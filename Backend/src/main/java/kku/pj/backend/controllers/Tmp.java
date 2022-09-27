package kku.pj.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tmp {
    @GetMapping("/api/a")
    public String a(){
        return "Anirut";
    }
}
