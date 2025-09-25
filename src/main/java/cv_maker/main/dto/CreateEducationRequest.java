package cv_maker.main.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEducationRequest {
    private String school;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
