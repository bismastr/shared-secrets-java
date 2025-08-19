package com.github.bismastr.sharedsecrets.answer.controller;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.service.AnswerService;
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
    public ResponseEntity<AnswerResponseDto> saveAnswer(@RequestBody AnswerResponseDto answerResponseDto) {
        AnswerResponseDto savedAnswer = answerService.saveAnswer(answerResponseDto);
        return ResponseEntity.ok(savedAnswer);
    }
}
