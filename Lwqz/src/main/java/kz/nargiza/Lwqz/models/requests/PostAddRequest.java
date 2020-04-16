package kz.nargiza.Lwqz.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAddRequest {
    @NotEmpty
    @NonNull
    private String title;

    @NotEmpty
    @NonNull
    private String description;

    @NotEmpty
    @NonNull
    private int price;

    @NonNull
    @NotEmpty
    private MultipartFile file;

    @NonNull
    @NotEmpty
    private String category;
}
