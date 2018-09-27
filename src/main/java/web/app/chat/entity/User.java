package web.app.chat.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"name", "password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
