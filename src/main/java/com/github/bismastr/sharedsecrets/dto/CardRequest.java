package com.github.bismastr.sharedsecrets.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequest {
    private String title;
    private String question;
    private boolean isFeatured;
}
