package cv_maker.main.controller;

import cv_maker.main.dto.CreateSkillRequest;
import cv_maker.main.dto.SkillResponse;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillSvc;
    private final UserRepository userRepository;

    public SkillController(SkillService skillSvc, UserRepository userRepository) {
        this.skillSvc = skillSvc;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<SkillResponse>> list(Authentication auth) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(skillSvc.listSkills(userId));
    }

    @PostMapping
    public ResponseEntity<SkillResponse> add(
            Authentication auth,
            @RequestBody CreateSkillRequest req) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        return ResponseEntity.ok(skillSvc.addSkill(userId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication auth,
            @PathVariable UUID id) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
        skillSvc.deleteSkill(userId, id);
        return ResponseEntity.noContent().build();
    }
}
