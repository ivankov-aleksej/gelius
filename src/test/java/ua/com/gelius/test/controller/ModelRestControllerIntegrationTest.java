package ua.com.gelius.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.gelius.test.exception.ErrorInfo;
import ua.com.gelius.test.exception.ErrorMessage;
import ua.com.gelius.test.exception.ErrorType;
import ua.com.gelius.test.model.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ModelRestControllerIntegrationTest {

    private final String PART_OF_URL = "/api/model";
    private final int ID = 1;

    private final int value1 = 2;
    private final int value2 = 3;


    private MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    ModelRestControllerIntegrationTest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void plus() throws Exception {
        final int resultPlus = 8;

        String content = mockMvc.perform(get(PART_OF_URL + "/plus/" + ID)
                .param("value1", String.valueOf(value1))
                .param("value2", String.valueOf(value2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Model model = objectMapper.readValue(content, Model.class);
        assertEquals(ID, model.getId());
        assertEquals(resultPlus, model.getValue());
    }

    @Test
    void minus() throws Exception {
        final int resultMinus = 2;

        String content = mockMvc.perform(get(PART_OF_URL + "/minus/" + ID)
                .param("value1", String.valueOf(value1))
                .param("value2", String.valueOf(value2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Model model = objectMapper.readValue(content, Model.class);
        assertEquals(ID, model.getId());
        assertEquals(resultMinus, model.getValue());
    }

    @Test
    void notFound() throws Exception {
        final int notFoundID = 10;

        String content = mockMvc.perform(get(PART_OF_URL + "/plus/" + notFoundID)
                .param("value1", String.valueOf(value1))
                .param("value2", String.valueOf(value2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        ErrorInfo errorInfo = objectMapper.readValue(content, ErrorInfo.class);
        assertEquals(ErrorMessage.NOT_FOUND_ID.getMessage() + notFoundID, errorInfo.getMessage());
        assertEquals(ErrorType.DATA_NOT_FOUND.getErrorMessage(), errorInfo.getError());
    }
}
