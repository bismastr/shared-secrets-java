package com.github.bismastr.sharedsecrets.dto;

import com.github.bismastr.sharedsecrets.dto.vote.VoteResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    UUID id;
    UUID cardId;
    @NotBlank(message = "Answer text cannot be empty")
    String answerText;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Set<VoteResponseDto> answerVotes;
}