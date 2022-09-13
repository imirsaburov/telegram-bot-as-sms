package uz.imirsaburov.telegramsms.repositories;

import uz.imirsaburov.telegramsms.entities.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    Optional<ChatEntity> findFirstByChatId(String chatId);

    Optional<ChatEntity> findFirstByPhone(String phone);
}
