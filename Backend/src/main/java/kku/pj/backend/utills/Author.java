package kku.pj.backend.utills;

import org.springframework.security.core.context.SecurityContext;

public class Author {

    public String getUsernameFromContext(SecurityContext securityContext){
        return  (String) securityContext.getAuthentication().getPrincipal();
    }

}
