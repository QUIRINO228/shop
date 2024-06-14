package com.shop.sendgrid.controller;

import com.shop.sendgrid.dto.EmailDto;
import com.shop.sendgrid.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-activate-link")
    public ResponseEntity<String> sendActivatedLink(@RequestBody EmailDto emailDto) {
        try {
            emailService.sendEmail(emailDto);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
