package kz.nargiza.Lwqz.services.impl;

import kz.nargiza.Lwqz.models.entities.Role;
import kz.nargiza.Lwqz.models.entities.User;
import kz.nargiza.Lwqz.models.errors.ErrorCode;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import kz.nargiza.Lwqz.repositories.RoleRepository;
import kz.nargiza.Lwqz.repositories.UserRepository;
import kz.nargiza.Lwqz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(String email, String username, String password) throws SystemServiceException {
        if(userRepository.findUserByUsernameAndActiveIsTrue(username).isPresent()){
            throw SystemServiceException.builder()
                    .errorCode(ErrorCode.USERNAME_ALREADY_EXISTS)
                    .message("User with such username already exists")
                    .build();

        }
        Optional<Role> userRole =this.roleRepository.findById(Role.ROLE_USER);
        if(userRole.isPresent()){
            Set<Role> set = new HashSet<Role>();
            set.add(userRole.get());
            return this.userRepository.save(User.builder()
                    .password(bCryptPasswordEncoder.encode(password))
                    .roles(set)
                    .username(username)
                    .email(email)
                    .build());
        }
        throw SystemServiceException.builder().errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .message("Role with such id not found")
                .build();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAllByActiveIsTrue();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findByIdAndActiveIsTrue(id).orElseThrow(() ->
                SystemServiceException.builder()
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .message("User with  id " + id + " not found")
                        .build()
        );
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsernameAndActiveIsTrue(username).orElseThrow(() ->
                SystemServiceException.builder()
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .message("User with  username " + username + " not found")
                        .build()
        );
    }

    @Override
    public User update(User user) throws SystemServiceException {
        if (Objects.nonNull(user.getId())) {
            return userRepository.save(user);
        }

        throw SystemServiceException.builder()
                .message("entity not found")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build();
    }

    @Override
    public void delete(User user) throws SystemServiceException {
        user.setActive(false);
        update(user);
    }

    @Override
    public void delete(Long id) throws SystemServiceException {
        User user = findById(id);
        delete(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findUserByUsernameAndActiveIsTrue(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), user.getRoles());
        } else {
            return null;
        }
    }
}
