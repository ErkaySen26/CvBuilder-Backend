package cv_maker.main.service;

import cv_maker.main.dto.CreateEducationRequest;
import cv_maker.main.dto.EducationResponse;
import cv_maker.main.model.Education;
import cv_maker.main.model.User;
import cv_maker.main.repository.EducationRepository;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.EducationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EducationServiceImpl implements EducationService {

    private final EducationRepository eduRepo;
    private final UserRepository userRepo;

    public EducationServiceImpl(EducationRepository eduRepo, UserRepository userRepo) {
        this.eduRepo = eduRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<EducationResponse> listEducation(UUID userId) {
        return eduRepo.findByUserId(userId).stream()
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public EducationResponse addEducation(UUID userId, CreateEducationRequest req) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User yok"));
        Education e = Education.builder()
                .user(u)
                .school(req.getSchool())
                .degree(req.getDegree())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .description(req.getDescription())
                .build();
        eduRepo.save(e);
        return map(e);
    }

    @Override
    public void deleteEducation(UUID userId, UUID educationId) {
        Education e = eduRepo.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Bulunamadı"));
        if (!e.getUser().getId().equals(userId))
            throw new RuntimeException("Yetkisiz");
        eduRepo.delete(e);
    }

    private EducationResponse map(Education e) {
        return EducationResponse.builder()
                .id(e.getId())
                .school(e.getSchool())
                .degree(e.getDegree())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .description(e.getDescription())
                .build();
    }
}
