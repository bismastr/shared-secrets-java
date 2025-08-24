package com.github.bismastr.sharedsecrets.card.service;


import com.github.bismastr.sharedsecrets.card.dto.CardDto;
import com.github.bismastr.sharedsecrets.card.mapper.CardMapper;
import com.github.bismastr.sharedsecrets.card.model.Card;
import com.github.bismastr.sharedsecrets.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Transactional(readOnly = true)
    public List<CardDto> getFeaturedCards(boolean featured) {
        log.debug("Retrieving all featured cards");
        List<Card> cardsList = cardRepository.findAllByFeatured(featured);
        List<CardDto> listFeaturedCards = cardsList
                .stream()
                .map(cardMapper::toDto)
                .toList();

        log.debug("Found {} featured cards ", listFeaturedCards);
        return listFeaturedCards;
    }

    @Transactional
    public CardDto saveCard(CardDto cardDto) {
        log.debug("Inserting new card: {}", cardDto);
        Card card = cardMapper.toEntity(cardDto);
        Card savedCard = cardRepository.save(card);
        CardDto savedCardDto = cardMapper.toDto(savedCard);
        log.debug("Inserted new card: {}", savedCardDto);
        return savedCardDto;
    }

    @Transactional(readOnly = true)
    public CardDto getCardById(UUID cardId) {
        log.debug("Retrieving card by ID: {}", cardId);
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + cardId));
        CardDto cardDto = cardMapper.toDto(card);
        log.debug("Found card: {}", cardDto);
        return cardDto;
    }
}
