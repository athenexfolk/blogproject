package kku.pj.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link kku.pj.backend.entities.UserEntity} entity
 */
@Data
public class LoginDto implements Serializable {
    private final String username;
    private final String password;
}