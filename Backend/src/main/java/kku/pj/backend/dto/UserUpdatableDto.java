package kku.pj.backend.dto;

import kku.pj.backend.entities.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdatableDto implements Serializable {
    private final String password;
    private final String email;
    private final String confirmPassword;

    public void updateToEntity(User user){
        if(password!=null)
            user.setPassword(password);
        if(email!=null)
            user.setEmail(email);
    }
}
