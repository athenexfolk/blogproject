package kku.pj.backend.services.exceptions;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsernameIsExistException extends Exception{
    public UsernameIsExistException(String msg){super(msg);}
}
