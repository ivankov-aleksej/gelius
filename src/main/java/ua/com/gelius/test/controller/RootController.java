package ua.com.gelius.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping({"/", "/index"})
    public String main() {
        return "index";
    }
}
