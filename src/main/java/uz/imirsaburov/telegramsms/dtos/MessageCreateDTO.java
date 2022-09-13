package uz.imirsaburov.telegramsms.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageCreateDTO {
    @NotBlank
    private String phone;
    @NotBlank
    private String content;
}
