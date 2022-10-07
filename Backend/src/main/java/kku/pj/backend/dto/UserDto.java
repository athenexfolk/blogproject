package kku.pj.backend.dto;

import kku.pj.backend.entities.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDto implements Serializable {
    private final String username;
    private final String email;
    private final Date create_at;

    public UserDto(User user){
        username = user.getUsername();
        email = user.getEmail();
        create_at = user.getCreate_at();
    }

}
