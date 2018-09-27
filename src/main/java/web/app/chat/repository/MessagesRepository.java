package web.app.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.app.chat.entity.Message;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByMessage(String message);
}
