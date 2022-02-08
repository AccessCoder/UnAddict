package de.unaddict.backend.controller;

import de.unaddict.backend.exceptions.UserAlreadyExistException;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.modules.UserDto;
import de.unaddict.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }



    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {

        try {
            UserData registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException | ParseException uaeEx) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDto);
    }

}
