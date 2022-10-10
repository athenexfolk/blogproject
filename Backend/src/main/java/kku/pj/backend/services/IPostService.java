package kku.pj.backend.services;

import kku.pj.backend.dto.PostThumbnailEntityDto;
import kku.pj.backend.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface IPostService extends CRUDService<PostEntity,Integer> {
    Page<PostThumbnailEntityDto> getThumbnail(int page, int size, Sort sort);
}
