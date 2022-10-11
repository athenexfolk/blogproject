package kku.pj.backend.dto.v1;

import kku.pj.backend.entities.V1.Post;
import lombok.Data;

@Data
public class PostContentDto {
    private String title;
    private String content;
    private String user_id;

    public Post toPostEntity(){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser_id(user_id);
        return post;
    }
}
