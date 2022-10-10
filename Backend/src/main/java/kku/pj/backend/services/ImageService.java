package kku.pj.backend.services;

import kku.pj.backend.entities.Image;
import kku.pj.backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Qualifier("ImageService")
public class ImageService implements IImageService{

    private final String imgRepoPath;
    private final ImageRepository imageRepository;

    public ImageService(@Value("${image.repository.path}") String imgRepoPath, ImageRepository imageRepository) {
        this.imgRepoPath = imgRepoPath;
        this.imageRepository = imageRepository;
    }


    @Override
    public String  byteArrayToImage(byte[] data,String formatType) throws IOException {

        formatType = formatType.contains("image/")?formatType.replace("image/",""):null;

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);

        UUID uuid = UUID.nameUUIDFromBytes(data);
        String path = repoPath(uuid.toString(), formatType);
        File newFile = new File(path);
        if(!newFile.exists())
            ImageIO.write(bImage2, formatType,  newFile);

        return path.replace("src/main/resources/static/","");
    }


    @Override
    public Image addImage(byte[] img, String type, String name, String alt, String username) throws IOException {

        String path = byteArrayToImage(img,type);

        Image image = new Image();
        image.setAlt(alt);
        image.setName(name);
        image.setUsername(username);
        image.setPath(path);

        try {
            image = imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return image;
    }


    private String repoPath(String... path){
        return imgRepoPath + "/" + String.join(".",path);
    }


}
