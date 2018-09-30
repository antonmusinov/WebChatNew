package web.app.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.chat.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
