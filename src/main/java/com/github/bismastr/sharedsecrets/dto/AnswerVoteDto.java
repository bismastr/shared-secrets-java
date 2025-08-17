package com.github.bismastr.sharedsecrets.dto;

import com.github.bismastr.sharedsecrets.model.Answer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVoteDto implements Serializable {
    UUID id;
    Answer answer;
    @NotNull
    @Size(max = 10)
    String voteType;
    @Size(max = 10)
    String emoji;
    @NotNull
    OffsetDateTime votedAt;
}