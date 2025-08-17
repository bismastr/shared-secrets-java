package com.github.bismastr.sharedsecrets.controller;

import com.github.bismastr.sharedsecrets.dto.AnswerDto;
import com.github.bismastr.sharedsecrets.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDto> saveAnswer(@RequestBody AnswerDto answerDto) {
        AnswerDto savedAnswer = answerService.saveAnswer(answerDto);
        return ResponseEntity.ok(savedAnswer);
    }
}
