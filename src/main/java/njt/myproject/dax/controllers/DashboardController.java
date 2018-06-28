package njt.myproject.dax.controllers;

import javassist.NotFoundException;
import njt.myproject.dax.dto.form.AddTaskForm;
import njt.myproject.dax.dto.form.EditTaskForm;
import njt.myproject.dax.dto.form.EditTaskListForm;
import njt.myproject.dax.helper.Helper;
import njt.myproject.dax.models.Task;
import njt.myproject.dax.models.TodoList;
import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.services.MyUserDetailsService;
import njt.myproject.dax.services.TaskService;
import njt.myproject.dax.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private final TodoListService todoListService;

    private final TaskService taskService;

    @Autowired
    public DashboardController(TaskService taskService, TodoListService todoListService) {
        this.taskService = taskService;
        this.todoListService = todoListService;
    }

    /*
           # ===============================
           # = MAIN PAGE ROUTE
           # ===============================
    */

    @RequestMapping(value = "/my-dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal) {
        System.out.println(">>>>>>>My dashboard route<<<<<<");
        User user = principal.getUser();

        List<UserHasTodoList> userHasTodoLists = todoListService.getTodoListByUserId(user.getId());

        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.addObject("userTaskLists", userHasTodoLists);
        model.addObject("editTaskListForm", new EditTaskListForm());
        model.addObject("addTaskForm", new AddTaskForm());
        model.addObject("editTaskForm", new EditTaskForm());

        model.setViewName("dashboard");
        return model;
    }

     /*
           # ===============================
           # = CRUD ROUTES FOR TASK LIST
           # ===============================
      */

    @RequestMapping(value = "/add-new-task-list")
    public String addNewTaskList(@RequestParam("task_name") String name,
                                 @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal,
                                 final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>add-new-task-list route<<<<<<");
        User user = principal.getUser();
        if(name.trim().isEmpty()){
            redirectAttributes.addFlashAttribute("flash_error_message", "Name is required");
        } else {
            TodoList todoList = todoListService.saveTodoList(name, user);
            if (todoList == null) {
                redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
            } else {
                redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
            }
        }
        return "redirect:my-dashboard";
    }

    @RequestMapping(value = "/edit-task-list")
    public String editTaskList(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal, final RedirectAttributes redirectAttributes,
                               @Valid @ModelAttribute("editTaskListForm") EditTaskListForm editTaskListForm, BindingResult result) {
        System.out.println(">>>>>>>edit-task-list route<<<<<<");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error_message", getErrors(result));
            return "redirect:my-dashboard";
        }
        User user = principal.getUser();

        TodoList todoList = null;
        try {
            todoList = todoListService.updateTodoList(editTaskListForm.getId(), editTaskListForm.getName(), user);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        if (todoList == null) {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        } else {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully edited");
        }
        return "redirect:my-dashboard";
    }

    @RequestMapping(value = "/delete-task-list")
    public String deleteTaskList(@RequestParam("id") String id, @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal,
                                 final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>delete-task-list route<<<<<<");
        User user = principal.getUser();
        if (Helper.isInteger(id)) {
            try {
                todoListService.deleteTodoList(Integer.parseInt(id), user.getId());
                redirectAttributes.addFlashAttribute("flash_success_message", "Successfully deleted");
            } catch (NotFoundException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
            }
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "Bad request");
        }
        return "redirect:my-dashboard";
    }


    /*
        # ===============================
        # = CRUD ROUTES FOR SINGLE TASK
        # ===============================
     */

    @PostMapping(value = "/add-task")
    public String addNewTask(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal, final RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute("addTaskForm") AddTaskForm addTaskForm, BindingResult result) {
        System.out.println(">>>>>>>add-task route<<<<<<");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error_message", getErrors(result));
            return "redirect:my-dashboard";
        }
        User user = principal.getUser();
        Task task = new Task();
        task.setName(addTaskForm.getName_n());
        task.setDueDate(addTaskForm.getDueDate_n());
        task.setPriority((byte) addTaskForm.getPriority_n());
        task.setDescription(addTaskForm.getDescription_n());

        try {
            task = taskService.saveTask(task, user, addTaskForm.getParent_id_n());
        } catch (NotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("flash_error_message", e.getMessage());
            return "redirect:my-dashboard";
        }

        if (task == null) {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        } else {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
        }
        return "redirect:my-dashboard";
    }


    @PostMapping(value = "/edit-task")
    public String editTask(@Valid @ModelAttribute("editTaskForm") EditTaskForm editTaskForm, BindingResult result,
                           final RedirectAttributes redirectAttributes, @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal) {
        System.out.println(">>>>>>>edit-task route<<<<<<");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error_message", getErrors(result));
            return "redirect:my-dashboard";
        }
        User user = principal.getUser();
        try {
            if (taskService.updateTask(editTaskForm, user) != null) {
                redirectAttributes.addFlashAttribute("flash_success_message", "Successfully edited");
            } else {
                redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("flash_error_message", e.getMessage());
        }
        return "redirect:my-dashboard";
    }


    @PostMapping(value = "/delete-task")
    public String deleteTask(@RequestParam("id") String id, @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal,
                             final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>delete-task route<<<<<<");
        User user = principal.getUser();
        if (Helper.isInteger(id)) {
            try {
                taskService.deleteTask(Integer.parseInt(id), user);
                redirectAttributes.addFlashAttribute("flash_success_message", "Successfully deleted task");
            } catch (NotFoundException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
            }
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "Bad request");
        }
        return "redirect:my-dashboard";
    }


    @ResponseBody
    @PostMapping(value = "/check-task", produces = "application/json")
    public ResponseEntity<Object> checkTask(@RequestParam("id") String id, @RequestParam("checked") String checked,
                                            @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal) {
        System.out.println(">>>>>>>check-task route<<<<<<");
        System.out.println(checked + " <-checked | id-> " + id);
        User user = principal.getUser();
        String message;
        boolean success = false;
        Task task = null;

        if (Helper.isInteger(id) && Helper.isInteger(checked)) {
            try {
                task = taskService.checkOrUncheckTask(Integer.parseInt(id), user, Integer.parseInt(checked));
                success = true;
                if (task.getDone() == (byte) 1) {
                    message = "Checked";
                } else {
                    message = "Unchecked";
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
                message = e.getMessage();
            }
        } else {
            message = "Bad Request";
        }

        String label = (new Helper()).getPanelColor(task);
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("success", success);
        map.put("label", label);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /*
       # ===============================
       # = PRIVATE AND HELPER FUNCTIONS
       # ===============================
    */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    private String getErrors(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError objectError : result.getAllErrors()) {
            if (sb.length() >= 1)
                sb.append("<br>");
            sb.append(objectError.getDefaultMessage());
        }
        return sb.toString();
    }

}
