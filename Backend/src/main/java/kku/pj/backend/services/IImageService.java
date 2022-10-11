package kku.pj.backend.services;

import kku.pj.backend.dto.ImageEntityDto;
import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public interface IImageService extends CRUDService<ImageEntity,Integer> {
    Page<ImageEntityDto> gets(String username, int page, int size, Sort sort);

    String saveImgToFileSystem(byte[] data, String formatType) throws IOException;
}
