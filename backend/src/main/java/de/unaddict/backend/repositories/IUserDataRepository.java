package de.unaddict.backend.repositories;

import de.unaddict.backend.modules.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDataRepository extends MongoRepository<UserData, String> {
    UserData findByName(String name);
    UserData findByEMail(String email);
}
