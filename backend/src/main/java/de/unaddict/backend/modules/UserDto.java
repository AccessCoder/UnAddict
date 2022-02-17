package de.unaddict.backend.modules;

import de.unaddict.backend.security.PasswordMatches;
import de.unaddict.backend.security.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    private int age;

    @NotNull
    @NotEmpty
    private int cigarettesSmokedEachDayLastYear;

    @NotNull
    @NotEmpty
    private int cigarettesBranchCategory;

    @NotNull
    @NotEmpty
    private int yearsSmoked;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;


}
