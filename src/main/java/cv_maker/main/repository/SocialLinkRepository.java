package cv_maker.main.repository;

import cv_maker.main.model.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SocialLinkRepository extends JpaRepository<SocialLink, UUID> {
    List<SocialLink> findByUserId(UUID userId);
}
