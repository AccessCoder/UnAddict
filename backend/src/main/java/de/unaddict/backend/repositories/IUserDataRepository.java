package de.unaddict.backend.repositories;

import de.unaddict.backend.modules.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDataRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findByName(String name);

    Optional<UserData> findByEmail(String email);
}
