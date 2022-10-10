package kku.pj.backend.dto;

import kku.pj.backend.entities.V1.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDto implements Serializable {
    private final String username;
    private final String password;
    private final String email;

    public User toUserEntity(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
