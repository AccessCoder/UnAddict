package de.unaddict.backend.controller;


import de.unaddict.backend.services.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class DashboardController {

    final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/lifetime")
    public int getGainedLifetime() {
        return 5;
    }

    @GetMapping("/nonsmoked")
    public double getNonSmoked(@RequestBody String token) throws ParseException {
        return service.getNonSmokedCigarettes(token);
    }

    @GetMapping("/savedmoney")
    public double getSavedMoney(@RequestBody String token) throws ParseException {
        return getNonSmoked(token)*5;
    }

    @GetMapping("timesmokefree")
    public String getTimeNonSmoked() throws ParseException {
        return service.getTimeNotSmoked("01-01-2022 00:00:00");
    }
}
