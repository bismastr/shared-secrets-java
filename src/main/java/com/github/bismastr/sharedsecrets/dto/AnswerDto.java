package com.github.bismastr.sharedsecrets.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto implements Serializable {
    UUID id;
    UUID cardId;
    @NotBlank(message = "Answer text cannot be empty")
    String answerText;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Set<AnswerVoteDto> answerVotes;
}