package web.app.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;
import web.app.chat.service.MessageService;
import web.app.chat.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class MainController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MainController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @RequestMapping(path = "login",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") String name,
                                        @RequestParam("password") String password) {

        userService.createUser(name, password);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "say",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> say(@RequestParam("name") String name,
                                      @RequestParam("say") String message) {

        messageService.createMessage(name, message);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/allUsers")
    public @ResponseBody
    List<User> online() {
        return userService.findAll();
    }

    @GetMapping("/allMessage")
    public  @ResponseBody
        List<Message> chat() {
        return messageService.findAll();
    }


}
