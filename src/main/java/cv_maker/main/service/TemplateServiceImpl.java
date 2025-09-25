package cv_maker.main.service;

import cv_maker.main.dto.TemplateResponse;
import cv_maker.main.repository.TemplateRepository;
import cv_maker.main.service.abstracts.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository repo;
    public TemplateServiceImpl(TemplateRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TemplateResponse> getAllTemplates() {
        return repo.findAll().stream()
                .map(t -> TemplateResponse.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .description(t.getDescription())
                        .previewUrl(t.getPreviewUrl())
                        .build())
                .collect(Collectors.toList());
    }
}
