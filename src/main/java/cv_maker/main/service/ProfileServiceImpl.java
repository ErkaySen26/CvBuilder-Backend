package cv_maker.main.service;

import cv_maker.main.dto.CreateProfileRequest;
import cv_maker.main.dto.ProfileResponse;
import cv_maker.main.model.Profile;
import cv_maker.main.model.User;
import cv_maker.main.repository.ProfileRepository;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepo;
    private final UserRepository userRepo;

    public ProfileServiceImpl(ProfileRepository profileRepo, UserRepository userRepo) {
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ProfileResponse getProfile(UUID userId) {
        Profile p = profileRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return map(p);
    }

    @Override
    public ProfileResponse createOrUpdateProfile(UUID userId, CreateProfileRequest req) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Profile p = profileRepo.findByUserId(userId)
                .orElse(Profile.builder().user(u).build());
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setBirthDate(req.getBirthDate());
        p.setPhotoUrl(req.getPhotoUrl());
        p.setSummary(req.getSummary());

        profileRepo.save(p);
        return map(p);
    }

    private ProfileResponse map(Profile p) {
        return ProfileResponse.builder()
                .userId(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .birthDate(p.getBirthDate())
                .summary(p.getSummary())
                .photoUrl(p.getPhotoUrl())
                .build();
    }
}
