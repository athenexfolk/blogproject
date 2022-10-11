package kku.pj.backend.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageNotExistException extends Exception{
    public ImageNotExistException(String msg) {
        super(msg);
    }
}
