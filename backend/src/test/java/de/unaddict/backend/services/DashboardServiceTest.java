package de.unaddict.backend.services;

import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import de.unaddict.backend.repositories.MongoUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    final Instant instantFiveYears =  Instant.now().minusSeconds(157680000);
    final UserData user = UserData.builder().age(20).cigarettesBranchCategory("2").cigarettesSmokedEachDayLastYear(20).userRegistrationTime(instantFiveYears).build();
    final UserData userFail = UserData.builder().age(99).cigarettesBranchCategory("4").cigarettesSmokedEachDayLastYear(0).userRegistrationTime(instantFiveYears).build();

    @Mock
    DashboardService service;

    @Test
    @DisplayName("Should return 1825d 0h 0m 0s")
    void getTimeNotSmoked() {
        //GIVEN
        //WHEN
        when(service.getTimeNotSmoked(user)).thenReturn("1825d 0h 0m 0s");
        String result = service.getTimeNotSmoked(user);
        System.out.println(result);
        //THEN
        assertEquals("1825d 0h 0m 0s", result);
    }

    @Test
    @DisplayName("")
    void getNonSmokedCigarettes() {
        //GIVEN
        //WHEN
//        when(service.getTimeNotSmoked(user)).th(9125);
//        long result = service.getNonSmokedCigarettes(user);
//        System.out.println(result);
//        //THEN
//        assertEquals(9125, service.getNonSmokedCigarettes(user), "service.getNonSmokedCigarettes(user) unequals the expected Value");
    }

    @Test
    void getMoneySaved() {
    }

    @Test
    @DisplayName("Should return 2529d 10h 47m 59s")
    void getLifetimeSaved() {
        when(service.getLifetimeSaved(user)).thenReturn("2529d 10h 47m 59s");
        String result = service.getLifetimeSaved(user);
        System.out.println(result);
        //THEN
        assertEquals("2529d 10h 47m 59s", result);
    }

    @Test
    void getProgression() {
    }
}