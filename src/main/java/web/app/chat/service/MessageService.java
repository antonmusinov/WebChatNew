package web.app.chat.service;

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

    public List<Message> findAll() {
        return messagesRepository.findAll();
    }

    public void createMessage(User user, String message) {
        Message msg = new Message(message, user);

        messagesRepository.save(msg);


    }

}
