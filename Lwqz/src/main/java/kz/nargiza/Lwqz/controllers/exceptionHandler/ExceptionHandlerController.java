package kz.nargiza.Lwqz.controllers.exceptionHandler;

import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SystemServiceException.class)
    public ResponseEntity<SystemServiceException> exception(SystemServiceException e) {
        return new ResponseEntity<SystemServiceException>(e, HttpStatus.BAD_REQUEST);
    }

}
