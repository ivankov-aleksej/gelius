package ua.com.gelius.test.controller;

import org.springframework.web.bind.annotation.*;
import ua.com.gelius.test.model.Model;
import ua.com.gelius.test.service.ModelService;

@RestController
@RequestMapping("/api/model")
public class ModelRestController {

    private final ModelService service;

    public ModelRestController(ModelService service) {
        this.service = service;
    }

    @GetMapping("/plus/{id}")
    public Model plus(
            @RequestParam int value1,
            @RequestParam int value2,
            @PathVariable Integer id) {
        return service.plus(value1, value2, id);
    }


    @GetMapping("/minus/{id}")
    public Model minus(
            @RequestParam int value1,
            @RequestParam int value2,
            @PathVariable Integer id) {
        return service.plus(value1, value2, id);
    }
}
