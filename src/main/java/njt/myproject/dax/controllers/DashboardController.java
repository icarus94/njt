package njt.myproject.dax.controllers;

import javassist.NotFoundException;
import njt.myproject.dax.dto.form.EditTaskForm;
import njt.myproject.dax.dto.form.EditTaskListForm;
import njt.myproject.dax.dto.form.RegistrationForm;
import njt.myproject.dax.helper.Helper;
import njt.myproject.dax.models.TodoList;
import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.services.MyUserDetailsService;
import njt.myproject.dax.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    TodoListService todoListService;

    @RequestMapping(value = "/my-dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal) {
        System.out.println(">>>>>>>My dashboard route<<<<<<");
        User user = principal.getUser();


        ModelAndView model = new ModelAndView();
        model.setViewName("dashboard");
        model.addObject("user", user);

        List<UserHasTodoList> userHasTodoLists = todoListService.getTodoListByUserId(user.getId());
        model.addObject("userTaskLists", userHasTodoLists);
        model.addObject("editTaskForm", new EditTaskForm());
        model.addObject("editTaskListForm", new EditTaskListForm());
        return model;
    }


    @RequestMapping(value = "/add-new-task-list")
    public String addNewTaskList(@RequestParam("task_name") String name,
                                 @AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal,
                                 final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>add-new-task-list route<<<<<<");
        User user = principal.getUser();
        TodoList todoList = todoListService.saveTodoList(name, user);
        if (todoList == null) {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        } else {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
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
            todoList = todoListService.updateTodoList(editTaskListForm.getId(), editTaskListForm.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        if (todoList == null) {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        } else {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
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

    public String addNewTask() {

        return "redirect:my-dashboard";
    }

    @PostMapping(value = "/edit-task")
    public String editTask(@Valid @ModelAttribute("editTaskForm") EditTaskForm editTaskForm, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("IMA GRESKE");
            for (ObjectError oe : result.getAllErrors()) {
                System.out.println(oe.getDefaultMessage());
            }
        } else {
            System.out.println("NEMA GRESKE");
        }
        System.out.println(editTaskForm);
        return "redirect:my-dashboard";
    }

    public String deleteTask() {

        return "redirect:my-dashboard";
    }

    @ResponseBody
    @PostMapping(value = "/check-task", produces = "application/json")
    public String checkTask(@RequestParam("id") String id, @RequestParam("checked") String checked) {
        System.out.println(checked + " here " + id);
        return "HEY";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);
    }


    private String getErrors(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError objectError : result.getAllErrors()) {
            if (sb.length() >= 1)
                sb.append("\n");
            sb.append(objectError.getDefaultMessage());
        }
        return sb.toString();
    }

}
