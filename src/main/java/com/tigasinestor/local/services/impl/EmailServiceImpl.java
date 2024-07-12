package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.dto.classbased.email.EmailDTO;
import com.tigasinestor.local.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMail(EmailDTO emailDTO) throws MessagingException, PresentException {
        try {
            MimeMessage message= javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDTO.getUserReceiver());
            helper.setSubject(emailDTO.getSubject());
            Context context = new Context();
            context.setVariable("message", emailDTO.getMessage()); // Se establece el mensaje del correo en la plantilla de html
            String html = templateEngine.process("email", context); // Se procesa la plantilla de html
            helper.setText(html, true); // Se establece el contenido del correo en formato html
            javaMailSender.send(message);
        }catch (Exception e){
            throw new PresentException("Error al enviar el correo" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
