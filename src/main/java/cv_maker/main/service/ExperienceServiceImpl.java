package cv_maker.main.service;

import cv_maker.main.dto.CreateExperienceRequest;
import cv_maker.main.dto.ExperienceResponse;
import cv_maker.main.model.Experience;
import cv_maker.main.model.User;
import cv_maker.main.repository.ExperienceRepository;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.ExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository expRepo;
    private final UserRepository userRepo;

    public ExperienceServiceImpl(ExperienceRepository expRepo, UserRepository userRepo) {
        this.expRepo = expRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<ExperienceResponse> listExperiences(UUID userId) {
        return expRepo.findByUserId(userId).stream()
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public ExperienceResponse addExperience(UUID userId, CreateExperienceRequest req) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Experience e = Experience.builder()
                .user(u)
                .company(req.getCompany())
                .jobTitle(req.getJobTitle())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .description(req.getDescription())
                .build();
        expRepo.save(e);
        return map(e);
    }

    @Override
    public void deleteExperience(UUID userId, UUID experienceId) {
        Experience e = expRepo.findById(experienceId)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
        if (!e.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");
        expRepo.delete(e);
    }

    private ExperienceResponse map(Experience e) {
        return ExperienceResponse.builder()
                .id(e.getId())
                .company(e.getCompany())
                .jobTitle(e.getJobTitle())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .description(e.getDescription())
                .build();
    }
}
