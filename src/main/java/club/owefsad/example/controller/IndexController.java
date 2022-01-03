package club.owefsad.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author owefsad
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/name")
    @ResponseBody
    public String name(String username) {
        return "Hello, " + username;
    }

    @RequestMapping("/age")
    @ResponseBody
    public String age(String age) {
        return "Hello, i was " + age + " yeas old.";
    }
}
