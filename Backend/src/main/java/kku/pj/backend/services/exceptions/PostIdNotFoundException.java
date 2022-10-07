package kku.pj.backend.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostIdNotFoundException extends Exception{
    public PostIdNotFoundException(String msg){
        super(msg);
    }
}
