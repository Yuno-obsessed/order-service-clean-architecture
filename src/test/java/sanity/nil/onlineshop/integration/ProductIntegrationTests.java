package sanity.nil.onlineshop.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.function.ServerResponse;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import sanity.nil.onlineshop.SpringProjectApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = SpringProjectApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
public class ProductIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    private static String BASE_URL = "http://localhost:8080/api/v1/country";


    @Test
    public void getCountry() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL + "/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(res -> ServerResponse.status(200))
                .andExpect(jsonPath("$.countryId").value(6))
                .andExpect(jsonPath("$.name").exists())
                .andReturn();

        String responseAsString = mvcResult.getResponse().getContentAsString();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void saveCountry() throws Exception {
//       MvcResult mvcResult = mockMvc.perform();
//    }
}
