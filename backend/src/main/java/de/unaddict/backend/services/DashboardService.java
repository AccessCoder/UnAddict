package de.unaddict.backend.services;

import de.unaddict.backend.modules.SmokeDataAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class DashboardService {

    @Autowired
     final SmokeDataAPI api;

    public DashboardService(SmokeDataAPI api) {
        this.api = api;
    }



    public String getTimeNotSmoked(String testTime) throws ParseException {
        return api.getTimeNotSmoked(testTime);
    }
}
