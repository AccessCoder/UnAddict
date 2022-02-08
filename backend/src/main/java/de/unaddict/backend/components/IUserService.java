package de.unaddict.backend.components;

import de.unaddict.backend.exceptions.UserAlreadyExistException;
import de.unaddict.backend.modules.UserData;
import de.unaddict.backend.modules.UserDto;

import java.text.ParseException;

public interface IUserService {
    UserData registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, ParseException;
}
