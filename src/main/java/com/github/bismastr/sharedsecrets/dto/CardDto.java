package com.github.bismastr.sharedsecrets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private UUID id;
    @NotBlank(message = "Question cannot be empty")
    @Size(min = 10, max = 1000, message = "Question must be between 10 and 1000 characters")
    private String question;
    private boolean featured;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
