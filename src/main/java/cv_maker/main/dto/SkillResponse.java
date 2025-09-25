package cv_maker.main.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillResponse {
    private UUID id;
    private String name;
    private String level;
}
