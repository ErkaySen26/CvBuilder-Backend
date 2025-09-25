package cv_maker.main.dto;

import java.util.UUID;

public record UserResponse(UUID id, String email, String password) {
}
