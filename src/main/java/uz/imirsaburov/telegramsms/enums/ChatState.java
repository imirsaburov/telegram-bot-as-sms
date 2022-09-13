package uz.imirsaburov.telegramsms.enums;

import lombok.Getter;

@Getter
public enum ChatState {

    START("/start");

    private final String message;

    ChatState(String message) {
        this.message = message;
    }

    public static ChatState getByMessage(String message) {
        for (ChatState value : ChatState.values()) {
            if (value.getMessage().equalsIgnoreCase(message))
                return value;
        }
        return START;
    }
}
