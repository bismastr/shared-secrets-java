package com.github.bismastr.sharedsecrets.answer.controller;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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

    @GetMapping("/{cardId}")
    public ResponseEntity<?> getAnswersByCardId(@PathVariable("cardId") UUID cardId,
                                                @PageableDefault(size = 100) Pageable pageable) {
        List<AnswerResponseDto> response = answerService.findAllByCardId(cardId, pageable);
        return ResponseEntity.ok(response);
    }
}
