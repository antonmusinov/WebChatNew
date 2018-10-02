package web.app.chat.service;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;
import web.app.chat.repository.MessagesRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<Message> findAll() {
        return messagesRepository.findAll();
    }

    public void createMessage(User user, String message, String time) {
        LocalTime localTime = new LocalTime();
        Message msg = new Message(message, user);
        msg.setTime(localTime.toString("hh:mm:ss"));

        messagesRepository.save(msg);
    }

}
