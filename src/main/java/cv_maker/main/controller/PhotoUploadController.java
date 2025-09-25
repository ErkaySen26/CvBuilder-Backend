package cv_maker.main.controller;

import com.cloudinary.Cloudinary;
import cv_maker.main.model.Profile;
import cv_maker.main.repository.ProfileRepository;
import cv_maker.main.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile-photo")
public class PhotoUploadController {

    private final Cloudinary cloudinary;
    private final UserRepository userRepo;
    private final ProfileRepository profileRepo;

    public PhotoUploadController(Cloudinary cloudinary, UserRepository userRepo, ProfileRepository profileRepo) {
        this.cloudinary = cloudinary;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(
            Authentication auth,
            @RequestParam("file") MultipartFile file) throws IOException {

        UUID userId = UUID.fromString(auth.getName());

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
        String url = uploadResult.get("secure_url").toString();

        Profile p = profileRepo.findByUserId(userId).orElseThrow();
        p.setPhotoUrl(url);
        profileRepo.save(p);

        return ResponseEntity.ok(url);
    }
}
