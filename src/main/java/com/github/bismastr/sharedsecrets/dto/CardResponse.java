package com.github.bismastr.sharedsecrets.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CardResponse {
    private UUID id;
    private String name;
    private String question;
    private boolean is_featured;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
