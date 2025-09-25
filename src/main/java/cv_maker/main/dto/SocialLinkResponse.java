package cv_maker.main.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLinkResponse {
    private UUID id;
    private String platform;
    private String url;
}
