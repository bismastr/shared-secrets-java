package com.github.bismastr.sharedsecrets.dto.vote;

import com.github.bismastr.sharedsecrets.model.enums.VoteType;
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
