package hello;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }
/*
    @Test
    public void getHelloByName() throws Exception {
        String expectedData = "{\"id\":1,\"content\":\"Hello, Stranger!\"}";

        mvc.perform(MockMvcRequestBuilders.get("/hello-world")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.content").value(expectedData)));

                .andExpect(content().string(containsString("{\"id\":1,\"content\":\"Hello, Stranger!\"}"))));

    }

    @Test
    public void getIsALoanSharkCard() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/2017/Ene/COFIDIS").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("La tarjeta COFIDIS con fecha 2017 Ene es usura")));
    }

 */
}
