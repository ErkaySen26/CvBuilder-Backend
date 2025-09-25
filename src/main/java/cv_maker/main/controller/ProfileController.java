package cv_maker.main.controller;

import cv_maker.main.dto.CreateProfileRequest;
import cv_maker.main.dto.ProfileResponse;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final UserRepository userRepository;
    public ProfileController(ProfileService profileService, UserRepository userRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(Authentication auth) {
        String email = auth.getName(); // Bu email, UUID değil!
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(profileService.getProfile(userId));
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> saveProfile(
            Authentication auth,
            @RequestBody CreateProfileRequest req) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(profileService.createOrUpdateProfile(userId, req));
    }


}
