package com.github.bismastr.sharedsecrets.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private UUID id;
    private String question;
    private boolean featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
