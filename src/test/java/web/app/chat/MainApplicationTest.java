package web.app.chat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import web.app.chat.controller.MainController;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private MainController mainController;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void loadController(){
        assertThat(mainController).isNotNull();
    }

    @Test
    public void loginTestStatusAndResponse() {

        int loginStatus = mainController.login("test").getStatusCodeValue();
        HttpStatus loginResponse = mainController.login("test").getStatusCode();

        assertThat(loginStatus).isEqualTo(200);
        assertThat(loginResponse).isEqualByComparingTo(OK);
    }

    @Test
    public void getLogin() {
        ResponseEntity<String> response = mainController.login("Test");

//        assertThat();
    }

}

