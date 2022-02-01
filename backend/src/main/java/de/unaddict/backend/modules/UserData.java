package de.unaddict.backend.modules;

import org.springframework.data.annotation.Id;

public class UserData {

    @Id
    String eMail;

    String name;
    String surname;
    String age;

    int cigarettesSmokedEachDayLastYear;
    int cigarettesBranchCategory; //1- Premium (Marlboro, Lucky Strike, etc.) 2- Discounter (EDEKA, etc.) 3- Selfmade
    int yearsSmoked;

}
