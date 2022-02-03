package de.unaddict.backend.services;

import de.unaddict.backend.components.MongoUserDetailsService;
import de.unaddict.backend.modules.SmokeDataAPI;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import de.unaddict.backend.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class DashboardService {

    IUserDataRepository repository;
    MongoUserDetailsService mongoUserDetailsService = new MongoUserDetailsService(repository);

    JWTUtils jwtUtils;

    UserData user = new UserData();

    @Autowired
     final SmokeDataAPI api;

    public DashboardService(SmokeDataAPI api) {
        this.api = api;
    }



    public String getTimeNotSmoked(String testTime) throws ParseException {
        return api.getTimeNotSmoked(testTime);
    }

    public double getNonSmokedCigarettes(String token) throws ParseException {
       user = mongoUserDetailsService.loadUserByMail(jwtUtils.extractUserEMail(token));
        return api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime());
    }
}
