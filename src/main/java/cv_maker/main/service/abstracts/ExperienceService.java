package cv_maker.main.service.abstracts;

import cv_maker.main.dto.CreateExperienceRequest;
import cv_maker.main.dto.ExperienceResponse;

import java.util.List;
import java.util.UUID;

public interface ExperienceService {
    List<ExperienceResponse> listExperiences(UUID userId);
    ExperienceResponse addExperience(UUID userId, CreateExperienceRequest req);
    void deleteExperience(UUID userId, UUID experienceId);
}
