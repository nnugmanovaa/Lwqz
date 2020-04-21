package kz.nargiza.Lwqz.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty
    @NonNull
    private String username;

    @NotEmpty
    @NonNull
    private String email;

    @NotEmpty
    @NonNull
    private String password;

}
