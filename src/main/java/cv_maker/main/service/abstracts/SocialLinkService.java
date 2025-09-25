package cv_maker.main.service.abstracts;

import cv_maker.main.dto.CreateSocialLinkRequest;
import cv_maker.main.dto.SocialLinkResponse;

import java.util.List;
import java.util.UUID;

public interface SocialLinkService {
    List<SocialLinkResponse> list(UUID userId);
    SocialLinkResponse add(UUID userId, CreateSocialLinkRequest req);
    void delete(UUID userId, UUID linkId);
}
