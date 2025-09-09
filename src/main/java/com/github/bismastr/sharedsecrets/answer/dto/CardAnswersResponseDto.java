package com.github.bismastr.sharedsecrets.answer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CardAnswersResponseDto {
    private UUID cardId;
    private List<AnswerResponseDto> answers;
}
