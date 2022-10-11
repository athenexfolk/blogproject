package kku.pj.backend.services;

import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.repositories.ImageReposiroty2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ImageService2 implements IImageService{
    private final ImageReposiroty2 imageReposiroty;


    public ImageService2(ImageReposiroty2 imageReposiroty) {
        this.imageReposiroty = imageReposiroty;
    }

    @Override
    public ImageEntity add(ImageEntity item) {
        return imageReposiroty.save(item);
    }

    @Override
    public ImageEntity get(Integer integer) {
        return imageReposiroty.findById(integer).get();
    }

    @Override
    public ImageEntity update(ImageEntity item) {
        return imageReposiroty.save(item);
    }

    @Override
    public boolean remove(ImageEntity item) {
        imageReposiroty.delete(item);
        return true;
    }

    @Override
    public Page<ImageEntity> gets(int page, int size, Sort sort) {
        return null;
    }
}
