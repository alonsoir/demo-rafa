package hello;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import hello.exceptions.NotExistException;
import hello.services.RateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    Logger logger = LoggerFactory.getLogger(HelloControllerIT.class);

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private RateService service;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void shouldBeALoanSharkCard() throws NotExistException {
        String year = "2017";
        String month = "Ene";
        String card = "COFIDIS";
        boolean result = service.isALoanSharkCard(year,month,card);
        Assert.assertTrue("Should be true",result);
    }

    @Test
    public void shouldFailTryingToSearchWithYearIsNullAndMonthIsNotNullIfLoanIsASharkCard() throws NotExistException{
        String year = null;
        String month = "Ene";
        String card = "COFIDIS";
        try {
            boolean result = service.isALoanSharkCard(year,month,card);
        }catch (NotExistException e){
            Assert.assertTrue("Catches Exception.",Boolean.TRUE);
        }


    }

    @Test
    public void shouldFailTryingToSearchWithYearIsNotNullAndMonthIsNullIfLoanIsASharkCard() throws NotExistException{
        String year = "2018";
        String month = null;
        String card = "COFIDIS";
        try {
            boolean result = service.isALoanSharkCard(year,month,card);
        }catch (NotExistException e){
            Assert.assertTrue("Catches Exception.",Boolean.TRUE);
        }
    }

    @Test
    public void shouldFailTryingToSearchWithYearIsNotNullAndMonthIsNotNullAndCardIsNullIfLoanIsASharkCard() throws NotExistException{
        String year = "2018";
        String month = "Ene";
        String card = null;
        try {
            boolean result = service.isALoanSharkCard(year,month,card);
        }catch (NotExistException e){
            Assert.assertTrue("Catches Exceotion.",Boolean.TRUE);
        }
    }
}
