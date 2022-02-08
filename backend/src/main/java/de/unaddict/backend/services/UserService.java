package de.unaddict.backend.services;

import de.unaddict.backend.components.IUserService;
import de.unaddict.backend.repositories.MongoUserDetailsService;
import de.unaddict.backend.exceptions.UserAlreadyExistException;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.modules.UserDto;
import de.unaddict.backend.repositories.IUserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserDataRepository repository;

    @Override
    public UserData registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, ParseException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        UserData user = new UserData(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setCigarettesSmokedEachDayLastYear(userDto.getCigarettesSmokedEachDayLastYear());
        user.setCigarettesBranchCategory(userDto.getCigarettesBranchCategory());
        user.setYearsSmoked(userDto.getYearsSmoked());

        return repository.save(user);
    }
    private boolean emailExist(String email) {
        return MongoUserDetailsService.loadUserByMail(email) != null;
    }
}
