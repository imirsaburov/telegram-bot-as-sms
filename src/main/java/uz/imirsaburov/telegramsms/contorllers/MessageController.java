package uz.imirsaburov.telegramsms.contorllers;

import uz.imirsaburov.telegramsms.dtos.MessageCreateDTO;
import uz.imirsaburov.telegramsms.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@Valid @RequestBody MessageCreateDTO dto) {
        messageService.create(dto);
    }
}
