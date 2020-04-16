package kz.nargiza.Lwqz.models.exceptions;

import kz.nargiza.Lwqz.models.errors.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemServiceException extends RuntimeException {
    private String message;
    private ErrorCode errorCode;
}
