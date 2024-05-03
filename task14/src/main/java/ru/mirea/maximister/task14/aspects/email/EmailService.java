package ru.mirea.maximister.task14.aspects.email;

import ru.mirea.maximister.task14.model.dto.EmailDto;

public interface EmailService {
    void send(EmailDto emailDto);
}
