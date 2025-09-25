package cv_maker.main.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @Column(name = "user_id")
    private UUID id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String photoUrl;
    @Column(columnDefinition = "TEXT")
    private String summary;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
