package de.unaddict.backend.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements UserDetails {

    SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    //gets date and time from now.
    LocalDateTime justNow = LocalDateTime.now();


    public UserData(String email, String name, String surname, String password, int age, int cigarettesSmokedEachDayLastYear, int cigarettesBranchCategory, int yearsSmoked, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.name = name;
        this.password=password;
        this.age=age;
        this.userRegistrationTime=justNow.toString();
        this.cigarettesSmokedEachDayLastYear = cigarettesSmokedEachDayLastYear;
        this.cigarettesBranchCategory = cigarettesBranchCategory;
        this.yearsSmoked = yearsSmoked;
        this.authorities = authorities;
    }

    public UserData (String email) throws ParseException {
        this.email=email;
        this.userRegistrationTime= obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))).toString();
    }

    @Id
    String email;

    String password;
    String name;
    String surname;
    int age;
    String userRegistrationTime;

    int cigarettesSmokedEachDayLastYear;
    int cigarettesBranchCategory; //1- Premium (Marlboro, Lucky Strike, etc.) 2- Discounter (EDEKA, etc.) 3- Selfmade
    int yearsSmoked;

    public Collection<? extends GrantedAuthority> authorities;

    public boolean isAccountNonExpired= true;
    public boolean isAccountNonLocked= true;
    public boolean isCredentialsNonExpired= true;
    public boolean isEnabled= true;

    @Override
    public String getUsername() {
        return email;
    }

}
