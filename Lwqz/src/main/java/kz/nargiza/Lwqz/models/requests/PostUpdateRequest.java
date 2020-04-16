package kz.nargiza.Lwqz.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    @NotEmpty
    @NonNull
    private String title;

    @NotEmpty
    @NonNull
    private String description;

    @NotEmpty
    @NonNull
    private int price;

}
