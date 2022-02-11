package de.unaddict.backend.repositories;

import de.unaddict.backend.modules.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    public static final String AUTHORITY_API_READWRITE = "API_READWRITE";

    private static IUserDataRepository repository;

    public MongoUserDetailsService(IUserDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEMail) throws UsernameNotFoundException {
        Optional<UserData> user = repository.findByEmail(userEMail);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }

    public static UserData loadUserByMail(String userEmail) throws UsernameNotFoundException{
        Optional<UserData> user = repository.findByEmail(userEmail);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }


}
