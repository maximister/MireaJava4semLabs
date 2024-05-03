package ru.mirea.maximister.task14.aspects.email;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.mirea.maximister.task14.model.dto.EmailDto;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class DefaultEmailService implements EmailService {

    @Value("${application.emails}")
    private String[] addresses;

    @Value("${application.mail.enabled}")
    private boolean enabled;

    private final JavaMailSender mailSender;

    public DefaultEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void send(EmailDto emailDto) {
        if (!enabled) {
            log.warn("Email service is non enabled!");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.addresses());
        message.setSubject(emailDto.header());
        message.setText(emailDto.body());
        mailSender.send(message);

        log.info("mail with header '{}' was sent to users '{}'", emailDto.header(), Arrays.toString(emailDto.addresses()));
    }

    @SneakyThrows
    @Around("execution(* add*(..)) && within(ru.mirea.maximister.task14.service..*)")
    public Object formMessageDto(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String header = String.format(
                "adding new object %s in service %s",
                methodSignature.getParameterNames()[0],
                methodSignature.getDeclaringType().getSimpleName()
        );
        String body = "New object was added";
        try {
            return joinPoint.proceed();
        } finally {
            EmailDto emailDto = new EmailDto(addresses, header, body);
            send(emailDto);
        }
    }
}
