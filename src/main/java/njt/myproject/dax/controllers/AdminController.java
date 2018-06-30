package njt.myproject.dax.controllers;

import javassist.NotFoundException;
import njt.myproject.dax.dto.UserAddMapper;
import njt.myproject.dax.dto.UserEditMapper;
import njt.myproject.dax.dto.form.AddUserForm;
import njt.myproject.dax.dto.form.EditUserForm;
import njt.myproject.dax.exceptions.PermissionDeniedException;
import njt.myproject.dax.helper.Helper;
import njt.myproject.dax.models.User;
import njt.myproject.dax.services.MyUserDetailsService;
import njt.myproject.dax.services.TaskService;
import njt.myproject.dax.services.TodoListService;
import njt.myproject.dax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class AdminController {

    private final UserService userService;
    private final TodoListService todoListService;
    private final TaskService taskService;

    @Autowired
    public AdminController(UserService userService, TodoListService todoListService, TaskService taskService) {
        this.userService = userService;
        this.todoListService = todoListService;
        this.taskService = taskService;
    }


    @RequestMapping(value = "/admin-view")
    public ModelAndView adminView(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal) {
        System.out.println(">>>>>>>admin-view route<<<<<<");
        User user = principal.getUser();

        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.addObject("users", userService.getAllUsers());
        model.addObject("addUserForm", new AddUserForm());
        model.addObject("editUserForm", new EditUserForm());

        model.setViewName("admin-view");
        return model;
    }


    @PostMapping(value = "/admin-create-user")
    public String createUser(@Valid @ModelAttribute("addUserForm") AddUserForm addUserForm, BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>admin-create-user route<<<<<<");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error_message", getErrors(result));
        }
        User user = UserAddMapper.INSTANCE.addUserFormToUser(addUserForm);
        user = userService.createNewUser(user);
        if (user != null) {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        }
        return "redirect:admin-view";
    }

    @PostMapping(value = "/admin-edit-user")
    public String editUser(@Valid @ModelAttribute("editUserForm") EditUserForm editUserForm, BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>admin-edit-user route<<<<<<");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error_message", getErrors(result));
        }
        User user = UserEditMapper.INSTANCE.editUserFormToUser(editUserForm);
        try {
            user = userService.editUser(user);
        } catch (NotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("flash_error_message", e.getMessage());
            return "redirect:admin-view";
        }
        if (user != null) {
            redirectAttributes.addFlashAttribute("flash_success_message", "Successfully saved");
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
        }
        return "redirect:admin-view";
    }

    @RequestMapping(value = "/user-dashboard/{id}")
    public ModelAndView userTasksView(@AuthenticationPrincipal MyUserDetailsService.MyUserPrincipal principal,
                                      @PathVariable(value = "id") String id, final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>admin-view route<<<<<<");
        User user = principal.getUser();
        ModelAndView model = new ModelAndView();

        if (Helper.isInteger(id)) {
            try {
                User dbUser = userService.findById(Integer.parseInt(id));
                model.addObject("user", user);
                model.addObject("userHasTodoList", todoListService.getTodoListByUserId(Integer.parseInt(id)));
                model.addObject("userTarget", dbUser);
                model.addObject("tasks", taskService.getUsersTask(dbUser));

                model.setViewName("admin-user-dashboard");
            } catch (NotFoundException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("flash_error_message", e.getMessage());
                model.setViewName("redirect:admin-view");
            }
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "Bad request");
            model.setViewName("redirect:admin-view");
        }
        return model;
    }

    @PostMapping(value = "/admin-delete-user")
    public String deleteUser(@RequestParam("id") String id, final RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>admin-delete-user route<<<<<<");
        if (Helper.isInteger(id)) {
            try {
                userService.deleteUserById(Integer.parseInt(id));
                redirectAttributes.addFlashAttribute("flash_success_message", "Successfully deleted");
            } catch (NotFoundException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
            }
        } else {
            redirectAttributes.addFlashAttribute("flash_error_message", "Bad request");
        }
        return "redirect:admin-view";
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
