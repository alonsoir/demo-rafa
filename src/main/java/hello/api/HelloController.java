package hello.api;

import hello.dto.Greeting;
import hello.exceptions.NotExistException;
import hello.services.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    //must be private
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    private static final String template = "Hello, %s!";
    //this is not a good idea since a REST service must be stateless, only should rely on singletons
    private final AtomicLong counter = new AtomicLong();

    HelloController(final RateService service) {
        this.service = service;
    }

    @Autowired
    private final RateService service;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    //http://localhost:8080/usura?year=2018&month=Ene&card=COFIDIS
    @GetMapping("/usura")
    @ResponseBody
    //can be package private
    public Greeting isLoan(@RequestParam(name = "year", required = false, defaultValue = "Stranger") String year,
                           @RequestParam(name = "month", required = false, defaultValue = "Stranger") String month,
                           @RequestParam(name = "card", required = false, defaultValue = "Stranger") String card) {

        String initialMsg = "La tarjeta " + card + " con fecha " + year + " " + month;
        //this is redundant
        boolean value = false;
        String msg;
        try {
            value = service.isALoanSharkCard(year, month, card);
            msg = value ? initialMsg + " es usura. " : initialMsg + " NO es usura";
        } catch (NotExistException e) {
            msg = e.getMessage();
            logger.info("msg is " + msg + "<--->" + e.getMessage());
        }
        return new Greeting(counter.incrementAndGet(), msg);
    }

}
