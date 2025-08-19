package com.github.bismastr.sharedsecrets.dto.answer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AnswerRequestDto {
    UUID cardId;
    @NotBlank(message = "Answer text cannot be empty")
    String answerText;
}