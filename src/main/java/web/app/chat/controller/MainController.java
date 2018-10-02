package web.app.chat.controller;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;
import web.app.chat.service.MessageService;
import web.app.chat.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MainController(final UserService userService,
                          final MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/chat")
    public String main(final Map<String, Object> model){
        Iterable<Message> messages = messageService.findAll();
        model.put("messages", messages);

        return "chat";
    }

    @PostMapping("/chat")
    public String say(@AuthenticationPrincipal User user,
                                      @RequestParam String message,
                                      LocalTime localTime,
                                      Map<String, Object> model) {

        messageService.createMessage(user, message, localTime.toString("hh:mm:ss"));

        Iterable<Message> messages = messageService.findAll();

        model.put("messages", messages);

        return "chat";
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
