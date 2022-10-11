package kku.pj.backend.dto;

import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.entities.PostThumbnailEntity;
import kku.pj.backend.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostThumbnailEntityDto implements Serializable {
    private final Integer id;
    private final String username;
    private final Integer imgId;
    private final String title;
    private final String shortContent;
    private final Date create_at;
    private final UserEntityDto userEntity;
    private final ImageEntityDto imageEntity;

    public PostThumbnailEntityDto(
            Integer id, String username, Integer imgId,
            String title, String shortContent,
            Date create_at,
            UserEntity userEntity,
            ImageEntity imageEntity
    ) {
        this.id = id;
        this.username = username;
        this.imgId = imgId;
        this.title = title;
        this.shortContent = shortContent;
        this.create_at = create_at;
        this.userEntity = new UserEntityDto(userEntity);
        this.imageEntity = new ImageEntityDto(imageEntity);
    }

    public PostThumbnailEntityDto(Integer id, String username, Integer imgId, String title, String shortContent, Date create_at) {
        this.id = id;
        this.username = username;
        this.imgId = imgId;
        this.title = title;
        this.shortContent = shortContent;
        this.create_at = create_at;
        userEntity = null;
        imageEntity = null;
    }

    public PostThumbnailEntityDto(PostThumbnailEntity post) {
        id = post.getId();
        username = post.getUsername();
        imgId = post.getImgId();
        title = post.getTitle();
        shortContent = post.getShortContent();
        create_at = post.getCreate_at();
        userEntity = new UserEntityDto(post.getUserEntity());
        imageEntity = new ImageEntityDto(post.getImageEntity());
    }
}
