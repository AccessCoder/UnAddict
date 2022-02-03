package de.unaddict.backend;

import de.unaddict.backend.components.MongoUserDetailsService;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

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
//        repository.deleteAll();
//
//        //add Users
//        String encodedPassword = encoder.encode("tom123");
//        repository.save(new UserData("tom@test.de", "tom", encodedPassword, 20, 3, 12, List.of(new SimpleGrantedAuthority(MongoUserDetailsService.AUTHORITY_API_READWRITE))));
//        encodedPassword = encoder.encode("tommy123");
//        repository.save(new UserData("tommy@test.de", "tommy", encodedPassword, 5, 1, 4, List.of(new SimpleGrantedAuthority(MongoUserDetailsService.AUTHORITY_API_READWRITE))));

        String encodedPassword = encoder.encode("tom123");
        repository.save(new UserData("tomtest", "tom2", encodedPassword, 20, 3, 12, List.of(new SimpleGrantedAuthority(MongoUserDetailsService.AUTHORITY_API_READWRITE))));

        // find Users
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (UserData user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByUsername('tommy'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("tommy"));

    }

}
