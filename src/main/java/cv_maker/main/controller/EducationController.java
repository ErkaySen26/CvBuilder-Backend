package cv_maker.main.controller;

import cv_maker.main.dto.CreateEducationRequest;
import cv_maker.main.dto.EducationResponse;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationService eduSvc;
    private final UserRepository userRepository;

    public EducationController(EducationService eduSvc, UserRepository userRepository) {
        this.eduSvc = eduSvc;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<EducationResponse>> list(Authentication auth) {
        String email = auth.getName(); // email geliyor
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(eduSvc.listEducation(userId));
    }

    @PostMapping
    public ResponseEntity<EducationResponse> add(
            Authentication auth,
            @RequestBody CreateEducationRequest req) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(eduSvc.addEducation(userId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication auth,
            @PathVariable UUID id) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        eduSvc.deleteEducation(userId, id);
        return ResponseEntity.noContent().build();
    }
}