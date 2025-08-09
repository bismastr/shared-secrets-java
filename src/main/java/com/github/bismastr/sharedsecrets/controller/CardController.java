package com.github.bismastr.sharedsecrets.controller;

import com.github.bismastr.sharedsecrets.dto.CardDto;
import com.github.bismastr.sharedsecrets.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/featured")
    public ResponseEntity<List<CardDto>> getFeaturedCards() {
        List<CardDto> featuredCards = cardService.getFeaturedCards(true);
        return ResponseEntity.ok(featuredCards);
    }
}
