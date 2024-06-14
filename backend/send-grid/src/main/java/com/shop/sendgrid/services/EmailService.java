package com.shop.sendgrid.services;

import com.shop.sendgrid.dto.EmailDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(EmailDto emailDto) throws Exception;
}
