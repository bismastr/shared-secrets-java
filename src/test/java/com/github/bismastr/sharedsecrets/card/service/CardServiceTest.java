package com.github.bismastr.sharedsecrets.card.service;

import com.github.bismastr.sharedsecrets.card.dto.CardDto;
import com.github.bismastr.sharedsecrets.card.mapper.CardMapper;
import com.github.bismastr.sharedsecrets.card.model.Card;
import com.github.bismastr.sharedsecrets.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private CardMapper cardMapper;
    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getFeaturedCards returns mapped featured cards")
    void getFeaturedCardsReturnsMappedFeaturedCards() {
        Card card = new Card();
        CardDto cardDto = new CardDto();
        when(cardRepository.findAllByFeatured(true)).thenReturn(List.of(card));
        when(cardMapper.toDto(card)).thenReturn(cardDto);
        List<CardDto> result = cardService.getFeaturedCards(true);
        assertThat(result).containsExactly(cardDto);
        verify(cardRepository).findAllByFeatured(true);
        verify(cardMapper).toDto(card);
    }

    @Test
    @DisplayName("getFeaturedCards returns empty list when no featured cards exist")
    void getFeaturedCardsReturnsEmptyListWhenNoFeaturedCardsExist() {
        when(cardRepository.findAllByFeatured(false)).thenReturn(Collections.emptyList());
        List<CardDto> result = cardService.getFeaturedCards(false);
        assertThat(result).isEmpty();
        verify(cardRepository).findAllByFeatured(false);
    }

    @Test
    @DisplayName("saveCard saves and returns mapped card")
    void saveCardSavesAndReturnsMappedCard() {
        CardDto inputDto = new CardDto();
        Card card = new Card();
        Card savedCard = new Card();
        CardDto savedDto = new CardDto();
        when(cardMapper.toEntity(inputDto)).thenReturn(card);
        when(cardRepository.save(card)).thenReturn(savedCard);
        when(cardMapper.toDto(savedCard)).thenReturn(savedDto);
        CardDto result = cardService.saveCard(inputDto);
        assertThat(result).isEqualTo(savedDto);
        verify(cardMapper).toEntity(inputDto);
        verify(cardRepository).save(card);
        verify(cardMapper).toDto(savedCard);
    }

    @Test
    @DisplayName("getCardById returns mapped card when found")
    void getCardByIdReturnsMappedCardWhenFound() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card();
        CardDto cardDto = new CardDto();
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardMapper.toDto(card)).thenReturn(cardDto);
        CardDto result = cardService.getCardById(cardId);
        assertThat(result).isEqualTo(cardDto);
        verify(cardRepository).findById(cardId);
        verify(cardMapper).toDto(card);
    }

    @Test
    @DisplayName("getCardById throws EntityNotFoundException when card not found")
    void getCardByIdThrowsExceptionWhenNotFound() {
        UUID cardId = UUID.randomUUID();
        when(cardRepository.findById(cardId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> cardService.getCardById(cardId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(cardId.toString());
        verify(cardRepository).findById(cardId);
    }
}

