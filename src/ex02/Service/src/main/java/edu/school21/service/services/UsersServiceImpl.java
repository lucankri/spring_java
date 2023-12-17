package edu.school21.service.services;

import edu.school21.service.models.User;
import edu.school21.service.repositories.CrudRepository;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplate") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);
        User user = new User(email, temporaryPassword);
        try {
            usersRepository.save(user);
        } catch (CrudRepository.EmailExistsException e) {
            temporaryPassword = null;
        }
        return temporaryPassword;
    }
}
