package club.owefsad.example.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author owefsad
 */
@RestController
@RequestMapping("/cmd")
public class cmdInjectionController {

    @GetMapping("/runtime")
    public String runtime(String cmd) throws IOException {
        Runtime.getRuntime().exec(cmd);
        return "cmd is :" + cmd;
    }
}
