package kku.pj.backend.services;

import java.io.IOException;

public interface IImageService {
    String byteArrayToImage(byte[] data, String formatType) throws IOException;
}
