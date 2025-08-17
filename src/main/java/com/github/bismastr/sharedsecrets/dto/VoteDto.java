package com.github.bismastr.sharedsecrets.dto;

import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.model.enums.VoteType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    UUID id;
    Answer answer;
    @NotNull
    @Size(max = 10)
    VoteType voteType;
    @Size(max = 10)
    String emoji;
    @NotNull
    OffsetDateTime votedAt;
}