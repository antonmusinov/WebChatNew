package web.app.chat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import web.app.chat.controller.MainController;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTest {

@Autowired
    private MainController mainController;


    @Test
    public void loadController(){
        assertThat(mainController).isNotNull();
    }

    @Test
    public void loginTestStatusAndResponse() {

        int loginStatus = mainController.login("test", "password").getStatusCodeValue();
        HttpStatus loginResponse = mainController.login("test", "password").getStatusCode();

        assertThat(loginStatus).isEqualTo(200);
        assertThat(loginResponse).isEqualByComparingTo(OK);
    }

/* TODO */


}

