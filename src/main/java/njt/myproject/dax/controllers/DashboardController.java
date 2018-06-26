package njt.myproject.dax.controllers;

import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.services.MyUserDetailsService;
import njt.myproject.dax.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println("Count"+userHasTodoLists.size());
        return model;
    }
}