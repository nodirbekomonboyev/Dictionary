package com.nodirverse.dictionary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultResponse {
    private int point;
    private List<String> wrongAnswers;
    private List<String> correctAnswers;
}
