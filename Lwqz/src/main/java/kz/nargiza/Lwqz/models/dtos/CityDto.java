package kz.nargiza.Lwqz.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private Long id;

    @NonNull
    @NotEmpty
    private String name;
}
