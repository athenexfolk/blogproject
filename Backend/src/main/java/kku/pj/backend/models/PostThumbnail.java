package kku.pj.backend.models;

import lombok.Data;

import java.util.Date;

@Data
public class PostThumbnail {
    private final Integer id;
    private final String title;
    private final Date create_at;
    private final String user_id;
}
