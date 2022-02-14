package de.unaddict.backend.services;

import de.unaddict.backend.repositories.MongoUserDetailsService;
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
        return api.getTimeNotSmoked(user.getUserRegistrationTime().toString());
    }

    public long getNonSmokedCigarettes(UserData user) throws ParseException {
        return api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime().toString());
    }

    public double getMoneySaved(UserData user) throws ParseException {
        return api.getMoneySavedPerCigarette(user.getCigarettesBranchCategory())* api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime().toString());
    }

    public String getLifetimeSaved(UserData user) throws ParseException {
        return api.getLifetimeSaved(user.getAge(), user.getUserRegistrationTime().toString());
    }
}
