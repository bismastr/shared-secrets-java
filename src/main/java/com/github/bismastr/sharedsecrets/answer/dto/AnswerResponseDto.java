package com.github.bismastr.sharedsecrets.answer.dto;

import com.github.bismastr.sharedsecrets.vote.dto.VoteCountResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AnswerResponseDto {
    UUID id;
    UUID cardId;
    String answerText;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    List<VoteCountResponseDto> voteCounts;
}