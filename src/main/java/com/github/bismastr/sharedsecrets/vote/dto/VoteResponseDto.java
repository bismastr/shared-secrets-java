package com.github.bismastr.sharedsecrets.vote.dto;

import com.github.bismastr.sharedsecrets.vote.enums.VoteType;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class VoteResponseDto {
    UUID id;
    UUID answerId;
    VoteType voteType;
    String emoji;
    OffsetDateTime votedAt;
}