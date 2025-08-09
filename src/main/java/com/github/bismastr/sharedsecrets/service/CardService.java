package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.CardDto;
import com.github.bismastr.sharedsecrets.mapper.CardMapper;
import com.github.bismastr.sharedsecrets.model.Card;
import com.github.bismastr.sharedsecrets.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .map(cardMapper::toCardDto)
                .toList();

        log.debug("Found {} featured cards DEBUG", listFeaturedCards);
        return listFeaturedCards;
    }
}
