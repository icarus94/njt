package njt.myproject.dax.controllers;

import njt.myproject.dax.dto.form.RegistrationForm;
import njt.myproject.dax.models.User;
import njt.myproject.dax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/login", "/register", "/"}, method = {RequestMethod.GET})
    public String loginRegisterPage(HttpServletRequest request, Model model) {
        if (request.getRequestURI().equals("/register")) {
            model.addAttribute("request_type", "register");
        } else {
            model.addAttribute("request_type", "login");
        }
        model.addAttribute("registrationForm", new RegistrationForm());
        return "login";
    }

    @PostMapping(value = "registerAction")
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        //validate
        if (result.hasErrors()) {
            System.out.println("ima errora");
            model.addAttribute("request_type", "register");
        } else { //save data
            System.out.println("nema errora");
            if (userService.findByEmail(registrationForm.getEmail()) == null) {
                User u = userService.createNewUser(registrationForm);
                if (u == null) {
                    redirectAttributes.addFlashAttribute("flash_error_message", "An error occurred");
                    return "redirect:register";
                } else {
                    System.out.println(u);
                    redirectAttributes.addFlashAttribute("flash_success_message", "Successfully registered");
                }
            } else {
                redirectAttributes.addFlashAttribute("flash_error_message", "User with given email already exists!");
                return "redirect:register";
            }
        }
        return "redirect:login";
    }
}
