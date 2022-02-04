package de.unaddict.backend.services;

import de.unaddict.backend.components.MongoUserDetailsService;
import de.unaddict.backend.modules.SmokeDataAPI;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import de.unaddict.backend.security.JWTUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class DashboardService {

    private final MongoUserDetailsService mongoUserDetailsService;
    private final SmokeDataAPI api;

    public DashboardService(MongoUserDetailsService mongoUserDetailsService, SmokeDataAPI api) {
        this.mongoUserDetailsService = mongoUserDetailsService;
        this.api = api;
    }


    public String getTimeNotSmoked(String testTime) throws ParseException {
        return api.getTimeNotSmoked(testTime);
    }

    public double getNonSmokedCigarettes(UserData user) throws ParseException {
        return api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime());
    }
}
