package kku.pj.backend.services;

import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.entities.PostEntity;

import java.io.IOException;

public interface IImageService extends CRUDService<ImageEntity,Integer> {
    String saveImgToFileSystem(byte[] data, String formatType) throws IOException;
}
