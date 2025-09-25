package cv_maker.main.controller;

import cv_maker.main.dto.TemplateResponse;
import cv_maker.main.service.abstracts.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplateService templateService;
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public ResponseEntity<List<TemplateResponse>> list() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
}
