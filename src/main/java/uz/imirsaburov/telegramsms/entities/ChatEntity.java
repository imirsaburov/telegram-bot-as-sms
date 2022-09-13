package uz.imirsaburov.telegramsms.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "chat")
@Entity
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id", unique = true, nullable = false)
    private String chatId;

    @Column(name = "phone", unique = true)
    private String phone;
}
