package web.app.chat.service;

import org.joda.time.LocalTime;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;
import web.app.chat.repository.MessagesRepository;

import java.util.List;

import static web.app.chat.constant.Const.LINK;

@Service
public class MessageService {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<Message> getAllMessages() {
        return messagesRepository.findAll();
    }

    public void createMessage(User user, String message) {
        LocalTime localTime = new LocalTime();

        if (message.trim().startsWith(LINK)) {
            String unsafe = "<a href=\"" + message + "\">" + message + "</a>";
            Message msg = new Message(protectedMessage(unsafe), user);
            msg.setTime(localTime.toString("hh:mm:ss"));
            messagesRepository.save(msg);
        } else {
            Message msg = new Message(protectedMessage(message), user);
            msg.setTime(localTime.toString("hh:mm:ss"));
            messagesRepository.save(msg);
        }
    }

    private String protectedMessage(String msg) {
        return  Jsoup.clean(msg, Whitelist.basic());
    }



}
