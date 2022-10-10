package kku.pj.backend.dto.v1;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class ImageRequestDto implements Serializable {
    private final String alt;
    private final String name;
}
