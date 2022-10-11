package kku.pj.backend.dto.v1;

import lombok.Data;

import java.util.Date;

@Data
public class PostThumbnailDto {
    private final Integer id;
    private final String title;
    private final Date create_at;
    private final String user_id;
}
