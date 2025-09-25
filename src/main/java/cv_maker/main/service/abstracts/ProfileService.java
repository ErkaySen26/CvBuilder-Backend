package cv_maker.main.service.abstracts;

import cv_maker.main.dto.CreateProfileRequest;
import cv_maker.main.dto.ProfileResponse;

import java.util.UUID;

public interface ProfileService {
    ProfileResponse getProfile(UUID userId);
    ProfileResponse createOrUpdateProfile(UUID userId, CreateProfileRequest request);
}
