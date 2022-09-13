package uz.imirsaburov.telegramsms.entities;

import lombok.Getter;
import lombok.Setter;
import uz.imirsaburov.telegramsms.enums.MessageStatus;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "message")
@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "chat_id")
    @ManyToOne()
    private ChatEntity chat;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatus status;
}
