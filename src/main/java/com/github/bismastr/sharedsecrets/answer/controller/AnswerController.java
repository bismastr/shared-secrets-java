package com.github.bismastr.sharedsecrets.answer.controller;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Answer", description = "Endpoints for managing answers")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    @Operation(summary = "Save an answer", description = "Saves a new answer to the database")
    public ResponseEntity<AnswerResponseDto> saveAnswer(@RequestBody AnswerResponseDto answerResponseDto) {
        AnswerResponseDto savedAnswer = answerService.saveAnswer(answerResponseDto);
        return ResponseEntity.ok(savedAnswer);
    }

    @GetMapping("/{cardId}")
    @Operation(summary = "Get answers by card ID", description = "Retrieves all answers associated with a specific card ID")
    public ResponseEntity<?> getAnswersByCardId(@PathVariable("cardId") UUID cardId,
                                                @PageableDefault(size = 100) Pageable pageable) {
        List<AnswerResponseDto> response = answerService.findAllByCardId(cardId, pageable);
        return ResponseEntity.ok(response);
    }
}
