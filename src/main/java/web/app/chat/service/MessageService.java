package web.app.chat.service;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class MessageService {

    private int antiSpamCount = 0;
    private String lastMessage = "";

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

        if (antiSpam(message, user)){
            log.info(user + "is spamming");
        }

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

    private boolean antiSpam(String msg, User user) {

        if (msg.equals(lastMessage)) {
            antiSpamCount++;
        } else {
            lastMessage = msg;
            antiSpamCount = 0;
        }
        if (antiSpamCount >= 3) {
            log.info("User " + user + " is spamming");
            antiSpamCount = 0;
            lastMessage = "";
            return true;
        }
        return false;
    }

}
