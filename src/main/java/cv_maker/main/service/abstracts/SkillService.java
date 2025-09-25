package cv_maker.main.service.abstracts;

import cv_maker.main.dto.CreateSkillRequest;
import cv_maker.main.dto.SkillResponse;

import java.util.List;
import java.util.UUID;

public interface SkillService {
    List<SkillResponse> listSkills(UUID userId);
    SkillResponse addSkill(UUID userId, CreateSkillRequest req);
    void deleteSkill(UUID userId, UUID skillId);
}
