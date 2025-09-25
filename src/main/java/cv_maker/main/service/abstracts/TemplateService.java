package cv_maker.main.service.abstracts;

import cv_maker.main.dto.TemplateResponse;

import java.util.List;

public interface TemplateService {
    List<TemplateResponse> getAllTemplates();
}
