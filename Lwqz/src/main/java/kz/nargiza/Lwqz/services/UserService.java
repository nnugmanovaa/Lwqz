package kz.nargiza.Lwqz.services;

import kz.nargiza.Lwqz.models.entities.User;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User registerUser(String email, String username, String password) throws SystemServiceException;

    List<User> findAll() throws SystemServiceException;

    User findById(Long id) throws SystemServiceException;

    User findByUsername(String username);

    User update(User user) throws SystemServiceException;

    void delete(User user) throws SystemServiceException;

    void delete(Long id) throws SystemServiceException;

}
