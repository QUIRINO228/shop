package com.shop.users.client;


import com.shop.sendgrid.dto.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sendgrid", url = "${application.config.send-grid-url}")
public interface SendGridClient {

    @PostMapping("/send-activate-link")
    ResponseEntity<String> sendActivatedLink(@RequestBody EmailDto emailDto);
}
