package cv_maker.main.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceResponse {
    private UUID id;
    private String company;
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
