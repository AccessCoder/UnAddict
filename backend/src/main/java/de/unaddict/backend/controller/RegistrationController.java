package de.unaddict.backend.controller;

import de.unaddict.backend.exceptions.UserAlreadyExistException;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.modules.UserDto;
import de.unaddict.backend.security.EmailDto;
import de.unaddict.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto registerUserAccount(@Valid @RequestBody UserDto userDto) throws UserAlreadyExistException, ParseException {

        UserData registered = userService.registerNewUserAccount(userDto);

//        userService.startEmailVerification(registered.getEmail());  -> Aktuell noch fehlerhafter Login auf den Mailserver. OAuth wäre ein Workaround, aber da wir morgen bereits Abgabe haben, belasse ich es dabei.
        return UserDto.builder()
                .email(registered.getEmail())
                .name(registered.getName())
                .age(registered.getAge())
                .cigarettesSmokedEachDayLastYear(registered.getCigarettesSmokedEachDayLastYear())
                .cigarettesBranchCategory(registered.getCigarettesBranchCategory())
                .yearsSmoked(registered.getYearsSmoked())
                .password(registered.getPassword())
                .matchingPassword(registered.getPassword())
                .build();
    }

    @PostMapping("/email")
    public boolean verificateEmail(@RequestBody @Valid EmailDto emailDto) {
        return userService.verificateEmailToken(emailDto);
    }
}
