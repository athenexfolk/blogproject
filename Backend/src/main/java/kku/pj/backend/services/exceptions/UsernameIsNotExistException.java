package kku.pj.backend.services.exceptions;


import lombok.NoArgsConstructor;

public class UsernameIsNotExistException extends Exception{
    public UsernameIsNotExistException(){super();}
    public UsernameIsNotExistException(String msg){super(msg);}
}
