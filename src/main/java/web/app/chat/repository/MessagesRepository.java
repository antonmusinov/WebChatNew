package web.app.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.chat.entity.Message;
import web.app.chat.entity.User;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {
}
