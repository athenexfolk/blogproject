package kku.pj.backend.dto;

import kku.pj.backend.entities.UserEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDto implements Serializable {
    private final String username;
    private final String password;
    private final String email;
    private final Integer imgId;

    public UserEntity toEntity(){
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setImgId(imgId);
        return user;
    }
}
