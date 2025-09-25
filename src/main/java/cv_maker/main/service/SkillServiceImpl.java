package cv_maker.main.service;

import cv_maker.main.dto.CreateSkillRequest;
import cv_maker.main.dto.SkillResponse;
import cv_maker.main.model.Skill;
import cv_maker.main.model.User;
import cv_maker.main.repository.SkillRepository;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepo;
    private final UserRepository userRepo;

    public SkillServiceImpl(SkillRepository skillRepo, UserRepository userRepo) {
        this.skillRepo = skillRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<SkillResponse> listSkills(UUID userId) {
        return skillRepo.findByUserId(userId).stream()
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public SkillResponse addSkill(UUID userId, CreateSkillRequest req) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Skill s = Skill.builder()
                .user(u)
                .name(req.getName())
                .level(req.getLevel())
                .build();
        skillRepo.save(s);
        return map(s);
    }

    @Override
    public void deleteSkill(UUID userId, UUID skillId) {
        Skill s = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        if (!s.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");
        skillRepo.delete(s);
    }

    private SkillResponse map(Skill s) {
        return SkillResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .level(s.getLevel())
                .build();
    }
}
