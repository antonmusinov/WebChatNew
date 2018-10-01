package web.app.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.app.chat.entity.Role;
import web.app.chat.entity.User;
import web.app.chat.repository.UsersRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository)
                       {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }

    public User findByUsername(User user) {
        return usersRepository.findByUsername(user.getUsername());
    }

    public void saveUser(User user) {

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(user);

    }

}
