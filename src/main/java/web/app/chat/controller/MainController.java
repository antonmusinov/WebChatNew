package web.app.chat.controller;

import org.joda.time.LocalTime;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static web.app.chat.controller.ConstController.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private int antiSpamCount = 0;

    private String lastMessage = "";

    private static List<String> usernames = new ArrayList<>();
    private static Queue<String> messages = new ConcurrentLinkedQueue<>();

    @RequestMapping(path = "login",
                    method = RequestMethod.POST,
                    consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") final String name) {
        if (name.isEmpty()) {
            log.info("It is not valid login");
            return ResponseEntity.badRequest().body("It is not valid login");
        }

        usernames.add(name);
        messages.add("user: " + COLOR_FONT_BLUE + name + FONT_CLOSE_TAG + " - logged in ");

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "say",
                    method = RequestMethod.POST,
                    consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> say(@RequestParam(required=false, name = "message") final String message,
                                      @RequestParam("name") final String name,
                                      final LocalTime localTime) {

        if (message.isEmpty()) {
            log.info("Not message");
            return ResponseEntity.badRequest().body("Input msg pls");
        }

        String userName = COLOR_FONT_BLUE + name + FONT_CLOSE_TAG + USER_SAYS;

        String msgTime = TIME_MESSAGE + COLOR_FONT_GREEN + localTime.toString("HH:mm:ss") + FONT_CLOSE_TAG;

        if (antiSpam(message, name)){
            log.info(name + "is spamming");
            logout(name);
        }

        if (message.trim().startsWith(LINK)) {
            String unsafe = "<a href=\"" + message + "\">" + message + "</a>";
            messages.add(msgTime + " " + userName + protectedMessage(unsafe));
            log.info(String.valueOf(messages));
            return ResponseEntity.ok().build();
        } else {
            messages.add(msgTime + " " + userName + protectedMessage(message));
            return ResponseEntity.ok().build();
        }
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
        return new ResponseEntity<>(usernames.stream()
                .map(Objects::toString)
                .collect(Collectors.joining( "\n" )),
                HttpStatus.OK);
    }

    @RequestMapping(path = "logout",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> logout(@RequestParam("name") final String name) {

        usernames.remove(name);
        messages.add("User Disconnected : " + name);

        return ResponseEntity.ok().build();
    }

    private String protectedMessage(final String msg) {
        return Jsoup.clean(msg, Whitelist.basic());
    }

    private boolean antiSpam(final String msg, final String name) {

        if (msg.equals(lastMessage)) {
            antiSpamCount++;
        } else {
            lastMessage = msg;
            antiSpamCount = 0;
        }

        if (antiSpamCount >= 3) {
            log.info("User " + name + " is spamming");
            antiSpamCount = 0;
            lastMessage = "";
            return true;
        }
        return false;
    }

}