package kku.pj.backend.utills;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilsConfig {
    @Bean
    @Scope("singleton")
    InitialUtil getInitialUtil(){
        return new InitialUtil();
    }
}
