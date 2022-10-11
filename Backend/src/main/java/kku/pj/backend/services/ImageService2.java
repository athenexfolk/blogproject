package kku.pj.backend.services;

import kku.pj.backend.dto.ImageEntityDto;
import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.repositories.ImageReposiroty2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService2 implements IImageService{

    private final ImageReposiroty2 imageReposiroty;
    private final String path;

    public ImageService2(
            ImageReposiroty2 imageReposiroty,
            @Value("${image.repository.path}") String path
    ) {
        this.imageReposiroty = imageReposiroty;
        this.path = path;
    }

    @Override
    public ImageEntity add(ImageEntity item) {
        return imageReposiroty.save(item);
    }

    @Override
    public ImageEntity get(Integer integer) {
        var img = imageReposiroty.findById(integer);
        return img.orElse(null);
    }

    @Override
    public ImageEntity update(ImageEntity item) {
        return null;
    }

    @Override
    public boolean remove(ImageEntity item) {
        try {
            imageReposiroty.delete(item);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Page<ImageEntity> gets(int page, int size, Sort sort) {
        return null;
    }

    @Override
    public Page<ImageEntityDto> gets(String username, int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page,size,sort);
        var a = imageReposiroty.findMyImg(username, pageable);
        return a;
    }

    @Override
    public String saveImgToFileSystem(byte[] data, String formatType) throws IOException {

        formatType = formatType.contains("image/")?formatType.replace("image/",""):null;

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);

        UUID uuid = UUID.nameUUIDFromBytes(data);
        String path = this.path + "/" + uuid + "." + formatType; //repoPath(uuid.toString(), formatType);
        File newFile = new File(path);
        if(!newFile.exists())
            ImageIO.write(bImage2, formatType,  newFile);

        return path.replace("src/main/resources/static/","");
    }


}
