package com.nodirverse.dictionary.controller;


import com.nodirverse.dictionary.dto.request.AnswerDTO;
import com.nodirverse.dictionary.dto.response.ResultResponse;
import com.nodirverse.dictionary.dto.response.RootResponse;
import com.nodirverse.dictionary.service.RootService;
import com.nodirverse.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.security.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class UserController {
    private final RootService rootService;
    private final UserService userService;

    @GetMapping("/get-dates")
    public ResponseEntity<List<String>> getDates(Principal principal){
        return ResponseEntity.ok(rootService.getDates(UUID.fromString(principal.getName())));
    }

    @GetMapping("/check-roots")
    public ResponseEntity<List<ResultResponse>> checkRoots(@RequestBody List<AnswerDTO> answers){
        return ResponseEntity.ok(rootService.checkRoots(answers));
    }

    @GetMapping("get-test-by-date")
    public ResponseEntity<List<RootResponse>> getTestByDate(@RequestBody Timestamp date){
        return ResponseEntity.ok(rootService.getTestByDate(date));
    }
}
