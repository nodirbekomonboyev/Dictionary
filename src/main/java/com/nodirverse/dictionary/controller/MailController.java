package com.nodirverse.dictionary.controller;

import com.nodirverse.dictionary.dto.request.MailMessageDTO;
import com.nodirverse.dictionary.dto.response.SendMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.nodirverse.dictionary.service.MailMessageService;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class MailController {

    private final MailMessageService mailMessageService;

    @PostMapping("/send-code")
    public SendMessageResponse sendMessage(@RequestBody MailMessageDTO request){
        return mailMessageService.sendVerification(request);
    }
}
