package web.app.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.app.chat.entity.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);

    User findByName(String name);
}
