package kku.pj.backend.dto;

import kku.pj.backend.entities.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntityUpdateableDto implements Serializable {
    private final String password ;
    private final String old_password ;
    private final String email;
    private final Integer imgId;

    public UserEntity updateEntity(UserEntity u){
        if(email!=null)
            u.setEmail(email);
        if(imgId!=null)
            u.setImgId(imgId);
         if(password!=null)
             u.setPassword(password);

        return u;
    }
}
