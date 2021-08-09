package ua.com.gelius.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.com.gelius.test.exception.NotFoundException;
import ua.com.gelius.test.model.Model;
import ua.com.gelius.test.repository.ModelRepository;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ModelServiceTest {

    @Mock
    private ModelRepository repository;

    @InjectMocks
    private ModelService service;

    private final int value1 = 2;
    private final int value2 = 3;

    private final Model model = new Model();

    private final Integer ID = 1;
    private final Integer wrongID = 10;

    {
        model.setId(ID);
        Integer VALUE = 3;
        model.setValue(VALUE);
    }

    @BeforeEach
    void setUp() {
        when(repository.findById(ID)).thenReturn(Optional.of(model));
        when(repository.findById(wrongID)).thenReturn(Optional.empty());
    }

    @Test
    void plus() {
        final int resultPlus = 8;

        Model model = service.plus(value1, value2, ID);

        assertTrue(Objects.nonNull(model));
        assertEquals(resultPlus, model.getValue());
        verify(repository, times(1)).findById(ID);
    }

    @Test
    void minus() {
        final int resultMinus = 2;

        Model model = service.minus(value1, value2, ID);

        assertTrue(Objects.nonNull(model));
        assertEquals(resultMinus, model.getValue());
        verify(repository, times(1)).findById(ID);
    }

    @Test
    void notFound() {
        assertThrows(NotFoundException.class, () -> service.plus(value1, value2, wrongID));
        verify(repository, times(1)).findById(wrongID);
    }
}
