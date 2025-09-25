package cv_maker.main.service;

import cv_maker.main.core.VerificationCodeGenerator;
import cv_maker.main.model.User;
import cv_maker.main.model.VerificationCode;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@EnableScheduling
public class VerificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final VerificationCodeRepository verificationCodeRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public VerificationService(VerificationCodeRepository verificationCodeRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void sendVerificationCode(String email) {
        String verificationCode = VerificationCodeGenerator.generateVerificationCode();
        VerificationCode codeEntity = new VerificationCode();
        codeEntity.setEmail(email);
        codeEntity.setCode(verificationCode);
        codeEntity.setExpirationDate(LocalDateTime.now().plusMinutes(15));

        verificationCodeRepository.save(codeEntity);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code: " + verificationCode);
        javaMailSender.send(message);
    }

    public boolean verifyCode(String email, String code) {
        Optional<VerificationCode> verificationCode = verificationCodeRepository.findByEmailAndCode(email, code);

        if (verificationCode.isPresent()) {
            VerificationCode vc = verificationCode.get();
            if (vc.getExpirationDate().isAfter(LocalDateTime.now())) {

                return true;
            }
        }
        return false;
    }

    @Scheduled(cron = "0 */1 * * * *") // For 1 minute
    @Transactional
    public String cleanExpiredCodes() {
        verificationCodeRepository.deleteByExpirationDateBefore(LocalDateTime.now());
        return "DB deleted";
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
