package cv_maker.main.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateResponse {
    private Long id;
    private String name;
    private String description;
    private String previewUrl;
}
