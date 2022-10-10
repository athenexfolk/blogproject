package kku.pj.backend.services;

import kku.pj.backend.entities.Image;

import java.io.IOException;

public interface IImageService {
    String byteArrayToImage(byte[] data, String formatType) throws IOException;

    Image addImage(byte[] img, String type, String name, String alt, String username) throws IOException;
}
