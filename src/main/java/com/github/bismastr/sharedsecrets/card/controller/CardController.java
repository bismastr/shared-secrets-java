package com.github.bismastr.sharedsecrets.card.controller;

import com.github.bismastr.sharedsecrets.card.dto.CardDto;
import com.github.bismastr.sharedsecrets.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CardDto> saveCard(@RequestBody CardDto cardDto) {
        CardDto savedCard = cardService.saveCard(cardDto);
        return ResponseEntity.ok(savedCard);
    }
}
