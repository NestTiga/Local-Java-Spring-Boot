package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.dto.classbased.email.EmailDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendMail(EmailDTO emailDTO) throws MessagingException, PresentException;
}
