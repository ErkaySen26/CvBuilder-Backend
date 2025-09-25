package cv_maker.main.controller;

import cv_maker.main.service.VerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    private final VerificationService verificationCodeService;

    public VerificationController(VerificationService verificationService) {
        this.verificationCodeService = verificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        verificationCodeService.sendVerificationCode(email);
        return ResponseEntity.ok("Verification code sent.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean verified = verificationCodeService.verifyCode(email, code);
        if (verified) {
            return ResponseEntity.ok("Email verified.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired verification code.");
        }
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        verificationCodeService.updatePassword(email, newPassword);
        return ResponseEntity.ok("Password successfully updated.");
    }
}
