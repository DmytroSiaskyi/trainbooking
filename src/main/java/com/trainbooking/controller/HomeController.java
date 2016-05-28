package com.trainbooking.controller;


import com.trainbooking.components.LoggedInUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController extends BaseController{

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model, LoggedInUser user) {
        if(!user.hasRole("ROLE_ADMIN") && !user.hasRole("ROLE_USER"))
            return "redirect:/login?form=1";
        return "redirect:/sales";
    }
}
