package web.app.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Iterable<Message> messages = messageService.getAllMessages();
        model.put("messages", messages);

        return "chat";
    }

    @PostMapping("/chat")
    public String say(@AuthenticationPrincipal User user,
                                      @RequestParam String message,
                                      Map<String, Object> model) {

        messageService.createMessage(user, message);

        Iterable<Message> messages = messageService.getAllMessages();

        model.put("messages", messages);

        return "chat";
    }

    /*@GetMapping("/chat/allMessages")
    public String chat(Model model) {
        Iterable<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "chat";
    }*/


    @GetMapping("/allUsers")
    public @ResponseBody
        List<User> online() {
        return userService.findAll();
    }
}
