package cv_maker.main.dto;

import cv_maker.main.model.Role;

import java.util.Set;

public record CreateUserRequest(String email, String password, Set<Role> authorities) {
}
