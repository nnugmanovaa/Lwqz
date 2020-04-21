package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.dtos.UserDto;
import kz.nargiza.Lwqz.models.entities.User;
import kz.nargiza.Lwqz.models.mappers.UserMapper;
import kz.nargiza.Lwqz.models.requests.UserRegisterRequest;
import kz.nargiza.Lwqz.models.responses.SuccessResponse;
import kz.nargiza.Lwqz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController extends BaseController{
    private UserService userService;
    private UserMapper userMapper;

   @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Validated UserDto userDto){
        User user = userMapper.toEntity(userDto);
        this.userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
    }

   @GetMapping
   @PreAuthorize("hasAnyRole('ROLE_USER')")
   public ResponseEntity<?> getAll(){
       return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
   }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return buildResponse(SuccessResponse.builder().message("user with id " + id + " deleted").build(), HttpStatus.OK);
    }

    @GetMapping("/check/{username}")
    public Map checkUsername(@PathVariable String username){
        User user = this.userService.findByUsername(username);
        return Collections.singletonMap("exists", Objects.nonNull(user));
    }

}
