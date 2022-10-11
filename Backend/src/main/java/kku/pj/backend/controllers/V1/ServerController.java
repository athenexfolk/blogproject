package kku.pj.backend.controllers.V1;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ServerController {

    @GetMapping("status")
    public String getStatus(){ return "Ready!!!"; }

}
