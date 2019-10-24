package hello.api;

import hello.dto.Greeting;
import hello.exceptions.NotExistException;
import hello.services.RateService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

//Otherwise junit will never know there are mocked instances
@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    private HelloController helloController;

    @Mock
    private RateService service;

    //as a best practice, initialize your test setup in a setup method
    @Before
    public void setUp()  {
        //Now we have a mock injected in the controller! I prefer this way, but you can inject by setter using @InjectMocks annotation
        helloController = new HelloController(service);
    }

    //I'd suggest to you to have meaningful test names. I like this one, read the TDD chapter of the clean code book :)
    @Test
    public void should_checkForLoanShark_when_isLoan() throws NotExistException {
        //given
        final String year = "2019";
        final String january = "JANUARY";
        final String card = "00012-2938-9889";

        //when
        helloController.isLoan(year, january, card);

        //then
        //This way of verification for mocks is fancy! I'd suggest to you to start using it
        then(service).should().isALoanSharkCard(year, january, card);
    }

    @Test
    public void should_returnUsura_when_isALoanShark() throws NotExistException {
        //given
        final String year = "2019";
        final String january = "JANUARY";
        final String card = "00012-2938-9889";

        //use BDD Mockito library to define behaviours in your mocks outside from the context
        given(service.isALoanSharkCard(year, january, card)).willReturn(true);

        //when
        final Greeting isALoanShark = helloController.isLoan(year, january, card);

        //then
        //use always assertions to make your tests self-evaluated
        assertThat(isALoanShark.getContent()).contains("es usura");
    }

    @Test
    public void should_returnNoEsUsura_when_isAFairLoan() throws NotExistException {
        //given
        final String year = "2019";
        final String january = "JANUARY";
        final String card = "00012-2938-9889";

        //use BDD Mockito library to define behaviours in your mocks outside from the context
        given(service.isALoanSharkCard(year, january, card)).willReturn(false);

        //when
        final Greeting isALoanShark = helloController.isLoan(year, january, card);

        //then
        //use always assertions to make your tests self-evaluated
        assertThat(isALoanShark.getContent()).contains("NO es usura");
    }
}