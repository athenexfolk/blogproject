package kku.pj.backend.dto;

import kku.pj.backend.entities.PostEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostEntityDto implements Serializable {
    private final Integer id;
    private final String username;
    private final Integer imgId;
    private final String title;
    private final String content;
    private final String shortContent;
    private final Date create_at;
    private final Date modified_at;
    private final UserEntityDto userEntity;
    private final ImageEntityDto imageEntity;

    public PostEntityDto(PostEntity post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.imgId = post.getImgId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.shortContent = post.getShortContent();
        this.create_at = post.getCreate_at();
        this.modified_at = post.getModified_at();
//        if(post!=null) {
            this.userEntity = new UserEntityDto(post.getUserEntity());
            this.imageEntity = new ImageEntityDto(post.getImageEntity());
//        }else {
//            this.userEntity = null;
//            this.imageEntity = null;
//        }
    }
}
