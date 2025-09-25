package cv_maker.main.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSocialLinkRequest {
    private String platform;
    private String url;
}
