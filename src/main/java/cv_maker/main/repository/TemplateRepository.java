package cv_maker.main.repository;

import cv_maker.main.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> { }
