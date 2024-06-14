package com.shop.sendgrid.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.shop.sendgrid.dto.EmailDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@NoArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public void sendEmail(EmailDto emailDto) throws Exception {
        try {
            Email from = new Email("test1231231ads@gmail.com");
            Email toEmail = new Email(emailDto.getToEmail());
            Content content = new Content("text/plain", emailDto.getBody());
            Mail mail = new Mail(from, "Subject", toEmail, content);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() != 202) {
                throw new RuntimeException("Failed to send email");
            }
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new Exception("Failed to send email", e);
        }
    }
}
