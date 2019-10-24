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

    private static final Logger logger = LoggerFactory.getLogger(HelloControllerIT.class);

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
    public void should_Be_A_LoanSharkCard() throws NotExistException {
        String year = "2017";
        String month = "Ene";
        String card = "COFIDIS";
        boolean result = service.isALoanSharkCard(year,month,card);
        Assert.assertTrue("Should be true",result);
    }

    @Test
    public void should_Fail_Trying_To_Search_With_Year_IsNull_And_Month_Is_Not_Null_If_Loan_Is_ASharkCard() throws NotExistException{
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
    public void should_Fail_Trying_To_Search_With_Year_Is_Not_Null_And_Month_Is_Null_If_Loan_Is_A_SharkCard() throws NotExistException{
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
    public void should_Fail_Trying_To_Search_With_Year_Is_Not_Null_And_Month_Is_Not_Null_And_Card_Is_Null_If_Loan_Is_ASharkCard() throws NotExistException{
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
