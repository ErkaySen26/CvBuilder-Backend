package cv_maker.main.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSkillRequest {
    private String name;
    private String level;
}
