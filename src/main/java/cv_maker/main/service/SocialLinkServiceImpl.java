package cv_maker.main.service;

import cv_maker.main.dto.CreateSocialLinkRequest;
import cv_maker.main.dto.SocialLinkResponse;
import cv_maker.main.model.SocialLink;
import cv_maker.main.model.User;
import cv_maker.main.repository.SocialLinkRepository;
import cv_maker.main.repository.UserRepository;
import cv_maker.main.service.abstracts.SocialLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialLinkRepository repo;
    private final UserRepository userRepo;

    public SocialLinkServiceImpl(SocialLinkRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public List<SocialLinkResponse> list(UUID userId) {
        return repo.findByUserId(userId).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public SocialLinkResponse add(UUID userId, CreateSocialLinkRequest req) {
        User u = userRepo.findById(userId).orElseThrow();
        SocialLink s = SocialLink.builder()
                .user(u)
                .platform(req.getPlatform())
                .url(req.getUrl())
                .build();
        return map(repo.save(s));
    }

    @Override
    public void delete(UUID userId, UUID linkId) {
        SocialLink s = repo.findById(linkId).orElseThrow();
        if (!s.getUser().getId().equals(userId)) throw new RuntimeException("Unauthorized");
        repo.delete(s);
    }

    private SocialLinkResponse map(SocialLink s) {
        return SocialLinkResponse.builder()
                .id(s.getId())
                .platform(s.getPlatform())
                .url(s.getUrl())
                .build();
    }
}
