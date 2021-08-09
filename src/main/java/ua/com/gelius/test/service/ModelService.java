package ua.com.gelius.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gelius.test.exception.ErrorMessage;
import ua.com.gelius.test.exception.NotFoundException;
import ua.com.gelius.test.model.Model;
import ua.com.gelius.test.repository.ModelRepository;

@Service
@Transactional(readOnly = true)
public class ModelService {

    private ModelRepository repository;

    public Model plus (int value1, int value2, int id) {
        Model model = repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ID.getMessage() + id));
        model.setValue(value1 + value2 + model.getValue());
        return model;
    }

    public Model minus (int value1, int value2, int id) {
        Model model = repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ID.getMessage() + id));
        model.setValue(value1 - value2 + model.getValue());
        return model;
    }
}
