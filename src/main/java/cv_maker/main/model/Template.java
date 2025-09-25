package cv_maker.main.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "templates")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String previewUrl;
    private String filePath; // HTML/CSS dosya yolu veya benzeri
}
