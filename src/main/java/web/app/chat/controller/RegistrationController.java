package web.app.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.app.chat.entity.Role;
import web.app.chat.entity.User;
import web.app.chat.repository.UsersRepository;

import java.util.Collections;

@Slf4j
@Controller
public class RegistrationController {

    private UsersRepository usersRepository;

    @Autowired
    public RegistrationController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/registration")
    public String registration(){

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        User userFromDb = usersRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            log.info("User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(user);

        return "redirect:/login";
    }

}
