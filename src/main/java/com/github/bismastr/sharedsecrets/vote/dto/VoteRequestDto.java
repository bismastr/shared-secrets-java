package com.github.bismastr.sharedsecrets.vote.dto;

import com.github.bismastr.sharedsecrets.vote.enums.VoteType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class VoteRequestDto {
    @NotNull
    private UUID answerId;
    @NotNull
    private VoteType voteType;
    @Size(max = 10)
    private String emoji;
}
