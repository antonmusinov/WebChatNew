package web.app.chat.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;



@Entity
@Table(name = "message")
@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"name", "message", "time"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_name")
    private User name;

    @Column(name = "message")
    private String message;

    @Column(name = "time")
    private String time;


}
