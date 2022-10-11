package kku.pj.backend.dto;

import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.entities.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostEntityInsertableDto implements Serializable {
    private final Integer imgId;
    private final String title;
    private final String content;
    private final String shortContent;

    public PostEntity toEntity(){
        PostEntity p = new PostEntity();
        p.setImgId(imgId);
        p.setTitle(title);
        p.setContent(content);
        p.setShortContent(shortContent);
        return p;
    }

    public PostEntity updateEntity(PostEntity post){
        if(imgId!=null)
            post.setImgId(imgId);
        if(title!=null)
            post.setTitle(title);
        if(content!=null)
            post.setContent(content);
        if(shortContent!=null)
            post.setShortContent(shortContent);
        return post;
    }

}
