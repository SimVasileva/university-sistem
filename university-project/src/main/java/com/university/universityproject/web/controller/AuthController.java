package com.university.universityproject.web.controller;

import com.university.universityproject.dto.UserLoginForm;
import com.university.universityproject.dto.UserRegisterForm;
import com.university.universityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class AuthController extends BaseController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView) {
        return super.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Validated UserRegisterForm userRegisterForm,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return super.view("register", modelAndView.addObject(userRegisterForm));
        }
        this.userService.registerUser(userRegisterForm);

        return super.redirect("login");
    }


    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Validated UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("login", modelAndView.addObject(userLoginForm));
        }
        this.userService.loginUser(userLoginForm);
        return super.redirect("/");
    }

    @PostMapping("/logout")
    public ModelAndView postLogout() {
        this.userService.logout();
        return super.redirect("/");
    }
}
