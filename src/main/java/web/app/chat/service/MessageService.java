package web.app.chat.service;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;
import web.app.chat.repository.MessagesRepository;
import web.app.chat.repository.UsersRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository, UsersRepository usersRepository) {
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
    }

    public void createMessage(String name, String message) {

        Message msg = new Message();
        LocalTime localTime = new LocalTime();

        User userMsg = usersRepository.findByName(name);


        msg.setName(userMsg);
        msg.setMessage(message);
        msg.setTime(localTime.toString("hh:mm:ss"));

        messagesRepository.save(msg);

    }

    public List<Message> findAll() {
        return messagesRepository.findAll();
    }

}
