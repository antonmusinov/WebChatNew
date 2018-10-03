package web.app.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.app.chat.entity.User;
import web.app.chat.service.UserService;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        User userFromDb = userService.findByUsername(user);

        if (userFromDb != null) {
            log.info("User exists!");
            return "registration";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }
}
