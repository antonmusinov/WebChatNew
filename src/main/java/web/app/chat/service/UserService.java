package web.app.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.chat.entity.User;
import web.app.chat.repository.UsersRepository;

import java.util.List;

@Service
public class UserService {

    private UsersRepository usersRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository)
                       //BCryptPasswordEncoder bCryptPasswordEncoder)
                       {
        this.usersRepository = usersRepository;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(String name, String password) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        usersRepository.save(user);
    }

    public void deleteUser(User name) {
        usersRepository.delete(name);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public List<User> findAllByName(String name){
        return usersRepository.findAllByName(name);
    }

    public User findByName (String name) {
        return usersRepository.findByName(name);
    }


}
