package de.unaddict.backend.modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements UserDetails {

    public Collection<? extends GrantedAuthority> authorities;
    public boolean isAccountNonExpired = true;
    public boolean isAccountNonLocked = true;
    public boolean isCredentialsNonExpired = true;
    public boolean isEnabled = true;



    @Id
    String email;
    String password;
    String name;
    int age;
    Instant userRegistrationTime;
    int cigarettesSmokedEachDayLastYear;
    String cigarettesBranchCategory; //1- Premium (Marlboro, Lucky Strike, etc.) 2- Discounter (EDEKA, etc.) 3- Selfmade
    int yearsSmoked;


    public UserData(String email, String name, String password, int age, int cigarettesSmokedEachDayLastYear, String cigarettesBranchCategory, int yearsSmoked, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.age = age;
        this.cigarettesSmokedEachDayLastYear = cigarettesSmokedEachDayLastYear;
        this.cigarettesBranchCategory = cigarettesBranchCategory;
        this.yearsSmoked = yearsSmoked;
        this.authorities = authorities;
    }
    public UserData(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
