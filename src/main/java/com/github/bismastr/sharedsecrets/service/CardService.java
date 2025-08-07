package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.CardResponse;
import com.github.bismastr.sharedsecrets.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public List<CardResponse> getFeaturedCards() {
        return cardRepository.findAllByFeatured(true)
                .stream().map(
                        card -> CardResponse.builder()
                                .id(card.getId())
                                .name(card.getName())
                                .question(card.getQuestion())
                                .is_featured(card.is_featured())
                                .created_at(card.getCreated_at())
                                .updated_at(card.getUpdated_at())
                                .build()
                ).toList();
    }
}
