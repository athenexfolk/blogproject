package kku.pj.backend.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("ImageService")
public class ImageService implements IImageService{

    private final String imgRepoPath;

    public ImageService(@Value("${image.repository.path}") String imgRepoPath) {
        this.imgRepoPath = imgRepoPath;
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


    private String repoPath(String... path){
        return imgRepoPath + "/" + String.join(".",path);
    }


}
