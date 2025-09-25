package cv_maker.main.service.abstracts;

import cv_maker.main.dto.CreateEducationRequest;
import cv_maker.main.dto.EducationResponse;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    List<EducationResponse> listEducation(UUID userId);
    EducationResponse addEducation(UUID userId, CreateEducationRequest req);
    void deleteEducation(UUID userId, UUID educationId);
}
