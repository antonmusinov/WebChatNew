package web.app.chat.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@Accessors(chain = true)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_name")
    private User username;

    private String message;

    private String time;

    public Message(){}

    public Message(String message, User user) {
        this.username = user;
        this.message = message;
    }
}
