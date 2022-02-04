package de.unaddict.backend.services;

import de.unaddict.backend.components.MongoUserDetailsService;
import de.unaddict.backend.modules.SmokeDataAPI;
import de.unaddict.backend.modules.UserData;
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

    public String getTimeNotSmoked(UserData user) throws ParseException {
        return api.getTimeNotSmoked(user.getUserRegistrationTime());
    }

    public long getNonSmokedCigarettes(UserData user) throws ParseException {
        return api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime());
    }


}
