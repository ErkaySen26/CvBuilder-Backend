package cv_maker.main.controller;

import cv_maker.main.dto.CreateExperienceRequest;
import cv_maker.main.dto.ExperienceResponse;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService expSvc;
    private final UserRepository userRepository;

    public ExperienceController(ExperienceService expSvc, UserRepository userRepository) {
        this.expSvc = expSvc;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> list(Authentication auth) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(expSvc.listExperiences(userId));
    }

    @PostMapping
    public ResponseEntity<ExperienceResponse> add(
            Authentication auth,
            @RequestBody CreateExperienceRequest req) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(expSvc.addExperience(userId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication auth,
            @PathVariable UUID id) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        expSvc.deleteExperience(userId, id);
        return ResponseEntity.noContent().build();
    }
}
