package cv_maker.main.controller;

import cv_maker.main.dto.CreateSocialLinkRequest;
import cv_maker.main.dto.SocialLinkResponse;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.SocialLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/social-links")
public class SocialLinkController {

    private final SocialLinkService svc;
    private final UserRepository userRepository;

    public SocialLinkController(SocialLinkService svc, UserRepository userRepository) {
        this.svc = svc;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<SocialLinkResponse>> list(Authentication auth) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email))
                .getId();
        return ResponseEntity.ok(svc.list(userId));
    }

    @PostMapping
    public ResponseEntity<SocialLinkResponse> add(
            Authentication auth,
            @RequestBody CreateSocialLinkRequest req) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email))
                .getId();
        return ResponseEntity.ok(svc.add(userId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication auth,
            @PathVariable UUID id) {
        String email = auth.getName();
        UUID userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email))
                .getId();
        svc.delete(userId, id);
        return ResponseEntity.noContent().build();
    }
}
