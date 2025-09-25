package cv_maker.main.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String photoUrl;
    private String summary;

}
