package de.unaddict.backend.services;

import de.unaddict.backend.components.IUserService;
import de.unaddict.backend.repositories.MongoUserDetailsService;
import de.unaddict.backend.exceptions.UserAlreadyExistException;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.modules.UserDto;
import de.unaddict.backend.repositories.IUserDataRepository;
import de.unaddict.backend.security.EmailConfig;
import de.unaddict.backend.security.EmailDto;
import de.unaddict.backend.security.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserDataRepository repository;
    private final EmailConfig emailConfig;
    private final JWTUtils jwtUtils;
    private final MongoUserDetailsService service;


    @Override
    public UserData registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, ParseException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        UserData user = new UserData(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setUserRegistrationTime(Instant.now());
        user.setCigarettesSmokedEachDayLastYear(userDto.getCigarettesSmokedEachDayLastYear());
        user.setCigarettesBranchCategory(userDto.getCigarettesBranchCategory());
        user.setYearsSmoked(userDto.getYearsSmoked());

        return repository.save(user);
    }
    private boolean emailExist(String email) {
        return service.loadUserByMail(email) != null;
    }

    public void startEmailVerification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("andro6@web.de");
        message.setTo(email);
        message.setSubject("Email Verification UnAddict");
        message.setText("Hallo \n" + "vielen Dank, das Sie sich entschieden haben, den Service von UnAddict zu testen \n" + "/email/?token=" + JWTUtils.createToken(new HashMap<>(), email));
        emailConfig.getJavaMailSender().send(message);
    }

    public boolean verificateEmailToken(EmailDto emailVerificationDto) {
        Claims claims = jwtUtils.parseClaim(emailVerificationDto.getToken());
        Optional<UserData> optionalUser = repository.findByEmail(claims.getSubject());
        UserData user = optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already verified");
        }
        user.setEnabled(true);
        repository.save(user);
        return true;
    }
}
