package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.User;
import kz.nargiza.Lwqz.models.requests.UserRegisterRequest;
import kz.nargiza.Lwqz.models.responses.SuccessResponse;
import kz.nargiza.Lwqz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public User registerUser(@Valid @RequestBody UserRegisterRequest request){
       return this.userService.registerUser(request.getUsername(),
               request.getEmail(), request.getPassword());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }



    @DeleteMapping("{id}")
    public SuccessResponse deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return SuccessResponse.builder().message("user with id " + id + " deleted").build();
    }

    @GetMapping("/check/{username}")
    public Map checkUsername(@PathVariable String username){
        User user = this.userService.findByUsername(username);
        return Collections.singletonMap("exists", Objects.nonNull(user));
    }
}
