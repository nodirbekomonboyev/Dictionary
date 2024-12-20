package com.nodirverse.dictionary.controller;


import com.nodirverse.dictionary.dto.request.CheckRootDTO;
import com.nodirverse.dictionary.dto.response.ResultResponse;
import com.nodirverse.dictionary.dto.response.RootResponse;
import com.nodirverse.dictionary.service.RootService;
import com.nodirverse.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class UserController {
    private final RootService rootService;
    private final UserService userService;

    @PostMapping("/check-roots")
    public ResponseEntity<ResultResponse> checkRoots(@RequestBody List<CheckRootDTO> answers){
        return ResponseEntity.ok(rootService.checkRoots(answers));
    }

    @PostMapping("/check-root-by-id")
    public ResponseEntity<ResultResponse> checkRoot(@RequestBody CheckRootDTO request){
        return ResponseEntity.ok(rootService.checkRoot(request));
    }
    @GetMapping("/get-dates")
    public ResponseEntity<List<String>> getDates(Principal principal){
        return ResponseEntity.ok(rootService.getDates(UUID.fromString(principal.getName())));
    }

    @GetMapping("/get-test")
    public ResponseEntity<List<RootResponse>> getTest(@RequestParam Integer number){
        return ResponseEntity.ok(rootService.getTest(number));
    }
    @GetMapping("get-test-by-date")
    public ResponseEntity<List<RootResponse>> getTestByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(rootService.getTestByDate(date));
    }
}
