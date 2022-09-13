package uz.imirsaburov.telegramsms.repositories;

import uz.imirsaburov.telegramsms.entities.MessageEntity;
import uz.imirsaburov.telegramsms.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByStatus(MessageStatus status);

}
