package com.nodirverse.dictionary.dto.response;

import com.nodirverse.dictionary.dto.request.MailMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SendMessageResponse {
    private MailMessageDTO dto;
    private LocalDateTime time;
}
