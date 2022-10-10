package kku.pj.backend.dto;

import kku.pj.backend.entities.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntityDto implements Serializable {
    private final String username;
    private final String email;
    private final Integer imgId;
    private final Date create_at;
    private final Date modified_at;
    private final ImageEntityDto imageEntity;

    public UserEntityDto(UserEntity user){
        if(user!=null) {
            username = user.getUsername();
            email = user.getEmail();
            imgId = user.getImgId();
            create_at = user.getCreate_at();
            modified_at = user.getModified_at();

            if (user.getImageEntity() != null)
                imageEntity = new ImageEntityDto(user.getImageEntity());
            else
                imageEntity = null;
        }else {
            username = null;
            email = null;
            imgId = null;
            create_at = null;
            modified_at = null;
            imageEntity = null;
        }
    }
}
