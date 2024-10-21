package com.nodirverse.dictionary.service;

import com.nodirverse.dictionary.dto.request.AnswerDTO;
import com.nodirverse.dictionary.dto.response.ResultResponse;
import com.nodirverse.dictionary.dto.response.RootResponse;
import com.nodirverse.dictionary.entity.RootEntity;
import com.nodirverse.dictionary.repository.RootRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RootService {
    private final RootRepository rootRepository;
    private final ModelMapper modelMapper;
    public List<String> getDates(UUID id) {
        List<RootEntity> list = rootRepository.findDistinctByOwnerIdOrderByCreatedDate(id);
        List<String> response = new ArrayList<>();
        list.forEach((i) -> {response.add(i.getCreatedDate().toString());} );
        return response;
    }

    public Boolean checkRoot(UUID rootId, String answer) {
        return null;
    }

    public List<ResultResponse> checkRoots(List<AnswerDTO> answers) {
        return null;
    }

    public List<RootResponse> getTestByDate(LocalDate date) {
        List<RootEntity> list = rootRepository.findRootEntitiesByCreatedDate(date);
        List<RootResponse> responses = new ArrayList<>();
        list.forEach((i) -> {responses.add(modelMapper.map(i, RootResponse.class));});
        return responses;
    }
}
