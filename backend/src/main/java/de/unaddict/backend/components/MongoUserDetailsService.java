package de.unaddict.backend.components;

import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.repositories.IUserDataRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    public static final String AUTHORITY_API_READWRITE = "API_READWRITE";

    private final IUserDataRepository repository;

    public MongoUserDetailsService(IUserDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEMail) throws UsernameNotFoundException {
        UserData user = repository.findByEMail(userEMail);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
