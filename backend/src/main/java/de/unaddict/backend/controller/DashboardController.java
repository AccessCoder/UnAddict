package de.unaddict.backend.controller;


import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.services.DashboardService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class DashboardController {

    final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/lifetime")
    public int getGainedLifetime(UsernamePasswordAuthenticationToken authToken) {
        UserData user = (UserData) authToken.getPrincipal();
        return 5;
    }

    @GetMapping("/nonsmoked")
    public double getNonSmoked(UsernamePasswordAuthenticationToken authToken) throws ParseException {
        UserData user = (UserData) authToken.getPrincipal();
        return service.getNonSmokedCigarettes(user);
    }

    @GetMapping("/savedmoney")
    public double getSavedMoney(UsernamePasswordAuthenticationToken authToken) throws ParseException {
        UserData user = (UserData) authToken.getPrincipal();
        return service.getNonSmokedCigarettes(user)*5;
    }

    @GetMapping("timesmokefree")
    public String getTimeNonSmoked(UsernamePasswordAuthenticationToken authToken) throws ParseException {
        UserData user = (UserData) authToken.getPrincipal();
        return service.getTimeNotSmoked(user);
    }
}
