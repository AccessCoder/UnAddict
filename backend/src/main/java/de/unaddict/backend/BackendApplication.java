package de.unaddict.backend;

import de.unaddict.backend.repositories.MongoUserDetailsService;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = IUserDataRepository.class)
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private IUserDataRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //add Users
//        String encodedPassword = encoder.encode("tom123");
//        UserData user = new UserData("tom@test.de", "tom", encodedPassword, 20, 20, 2, 12, List.of(new SimpleGrantedAuthority(MongoUserDetailsService.AUTHORITY_API_READWRITE)));
//        user.setUserRegistrationTime(Instant.now().minusSeconds(2592000));
//        repository.save(user);

    }

}
