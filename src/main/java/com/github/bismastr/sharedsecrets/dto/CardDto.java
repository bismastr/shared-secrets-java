package com.github.bismastr.sharedsecrets.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private UUID id;
    private String name;
    private String question;
    private boolean featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
