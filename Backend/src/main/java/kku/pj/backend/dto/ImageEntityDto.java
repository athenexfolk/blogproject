package kku.pj.backend.dto;

import kku.pj.backend.entities.ImageEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImageEntityDto implements Serializable {
    private final Integer imgId;
    private final String username;
    private final String name;
    private final String alt;
    private final String url;

    public ImageEntityDto(ImageEntity image){
        if(image!=null) {
            imgId = image.getImgId();
            username = image.getUsername();
            name = image.getName();
            alt = image.getAlt();
            url = image.getUrl();
        }else {
            imgId = null;
            username = null;
            name = null;
            alt = null;
            url = null;
        }
    }
}
