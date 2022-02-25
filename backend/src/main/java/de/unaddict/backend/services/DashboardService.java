package de.unaddict.backend.services;

import de.unaddict.backend.modules.SmokeDataAPI;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.MongoUserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class DashboardService {

    private final SmokeDataAPI api;

    private final MongoUserDetailsService service;

    public DashboardService(SmokeDataAPI api, MongoUserDetailsService service) {
        this.api = api;
        this.service = service;
    }

    public String getTimeNotSmoked(UserData user) {
        return api.getTimeNotSmoked(user.getUserRegistrationTime());
    }

    public long getNonSmokedCigarettes(UserData user) {
        return api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime());
    }

    public double getMoneySaved(UserData user) {
        return api.getMoneySavedPerCigarette(user.getCigarettesBranchCategory()) * api.getNonSmokedCigarettes(user.getCigarettesSmokedEachDayLastYear(), user.getUserRegistrationTime());
    }

    public String getLifetimeSaved(UserData user) {
        return api.getLifetimeSaved(user.getAge(), user.getUserRegistrationTime());
    }

    public double getProgression(UserData user) {
        return api.getProgressionInPercent(user.getUserRegistrationTime());
    }
}
