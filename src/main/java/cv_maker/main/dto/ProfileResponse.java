package cv_maker.main.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String photoUrl;
    private String summary;

}
