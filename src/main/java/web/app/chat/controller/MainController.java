package web.app.chat.controller;

import org.joda.time.LocalTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class MainController {

    private static final String USER_SAYS = " says :";

    private static final String TIME_MESSAGE = " time : ";

    private  List<String> logins = new ArrayList<>();
    private Queue<String> messages = new ConcurrentLinkedQueue<>();

    @RequestMapping(path = "login",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") final String name) {
        if (name.length() < 1) {
            return ResponseEntity.badRequest().body("It is not valid login");
        }

        logins.add(name);
        messages.add("user: " + name + " - logged in ");

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "say",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> say(@RequestParam(required=false, name = "message") final String message,
                                      @RequestParam("name") final String name,
                                      final LocalTime localTime) {

        if (message.length() < 1) {
            return ResponseEntity.badRequest().body("Input msg pls");
        }

        messages.add(name + USER_SAYS  +  message +"\n" + TIME_MESSAGE + localTime.toString());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "chat",
                    method = RequestMethod.GET,
                    produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> chat(){
        return new ResponseEntity<>(messages.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n")),
                HttpStatus.OK);
    }

    @RequestMapping(path = "online",
                    method = RequestMethod.GET,
                    produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> online(){
        return new ResponseEntity<>(logins.stream()
                                    .map(Objects::toString)
                                    .collect(Collectors.joining( "\n" )),
                                    HttpStatus.OK);
    }

    @RequestMapping(path = "logout",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> logout(@RequestParam("name") final String name) {

        logins.remove(name);
        messages.add("User Disconnected : " + name);

        return ResponseEntity.ok().build();
    }
}
