package com.github.bismastr.sharedsecrets.answer.dto;

import com.github.bismastr.sharedsecrets.vote.dto.VoteResponseDto;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AnswerResponseDto {
    UUID id;
    UUID cardId;
    String answerText;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Set<VoteResponseDto> answerVotes;
}