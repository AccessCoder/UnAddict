package de.unaddict.backend.modules;

import de.unaddict.backend.security.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
@Builder
public class UserDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private int age;

    @NotNull
    @NotEmpty
    private int cigarettesSmokedEachDayLastYear;

    @NotNull
    @NotEmpty
    private String cigarettesBranchCategory;

    @NotNull
    @NotEmpty
    private int yearsSmoked;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @Email(message = "Email not valid")
    @NotNull
    @NotEmpty
    private String email;


}
