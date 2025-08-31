package com.github.bismastr.sharedsecrets.answer.controller;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

//    @GetMapping("/{cardId}")
//    public ResponseEntity<?> getAnswersByCardId(@PathVariable("cardId") String cardId) {
//        var response = answerService.getAnswerByCardId(java.util.UUID.fromString(cardId));
//        return ResponseEntity.ok(response);
//    }
}
