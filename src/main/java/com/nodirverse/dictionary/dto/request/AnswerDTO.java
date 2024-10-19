package com.nodirverse.dictionary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class AnswerDTO {
    private List<UUID> roots;
    private List<String> answers;
}
