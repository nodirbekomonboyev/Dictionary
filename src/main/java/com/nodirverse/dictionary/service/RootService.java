package com.nodirverse.dictionary.service;

import com.nodirverse.dictionary.dto.request.CheckRootDTO;
import com.nodirverse.dictionary.dto.response.ResultResponse;
import com.nodirverse.dictionary.dto.response.RootResponse;
import com.nodirverse.dictionary.entity.RootEntity;
import com.nodirverse.dictionary.exception.DataNotFoundException;
import com.nodirverse.dictionary.repository.RootRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public ResultResponse checkRoot(CheckRootDTO request) {
        RootEntity root = rootRepository.findById(request.getRootId()).orElseThrow(
                () -> new DataNotFoundException("root not found!")
        );
        ResultResponse response = new ResultResponse();
        if(root.getEng().equalsIgnoreCase(request.getAnswer())){
            response.setPoint(1);

        }else {
            List<String> correct = new ArrayList<>();
            correct.add(root.getEng());
            response.setCorrectAnswers(correct);
            List<String> wrong = new ArrayList<>();
            wrong.add(request.getAnswer());
            response.setWrongAnswers(wrong);
            response.setPoint(0);
        }
        return response;
    }

//    public List<ResultResponse> checkRoots(List<AnswerDTO> answers) {
//        return null;
//    }

    public List<RootResponse> getTestByDate(LocalDate date) {
        List<RootEntity> list = rootRepository.findRootEntitiesByCreatedDate(date);
        List<RootResponse> responses = new ArrayList<>();
        list.forEach((i) -> {responses.add(modelMapper.map(i, RootResponse.class));});
        return responses;
    }
}
