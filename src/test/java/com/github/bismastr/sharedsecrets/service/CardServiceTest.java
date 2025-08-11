package com.github.bismastr.sharedsecrets.service;

import com.github.bismastr.sharedsecrets.dto.CardDto;
import com.github.bismastr.sharedsecrets.mapper.CardMapper;
import com.github.bismastr.sharedsecrets.model.Card;
import com.github.bismastr.sharedsecrets.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardService cardService;

    @Test
    void getFeaturedCards_WhenCardsExist_ShouldReturnFeaturedCards() {
        // Arrange
        boolean isFeatured = true;
        Card card1 = new Card();
        card1.setId(UUID.randomUUID());
        card1.setQuestion("Question");
        card1.setFeatured(true);
        card1.setCreatedAt(LocalDateTime.now());
        card1.setUpdatedAt(LocalDateTime.now());

        Card card2 = new Card();
        card2.setId(UUID.randomUUID());
        card1.setQuestion("Question");
        card2.setFeatured(true);
        card2.setCreatedAt(LocalDateTime.now());
        card2.setUpdatedAt(LocalDateTime.now());

        CardDto cardDto1 = CardDto.builder()
                .id(card1.getId())
                .question(card1.getQuestion())
                .featured(card1.isFeatured())
                .createdAt(card1.getCreatedAt())
                .updatedAt(card1.getUpdatedAt())
                .build();

        CardDto cardDto2 = CardDto.builder()
                .id(card2.getId())
                .question(card2.getQuestion())
                .featured(card2.isFeatured())
                .createdAt(card2.getCreatedAt())
                .updatedAt(card2.getUpdatedAt())
                .build();

        List<Card> featuredCards = List.of(card1, card2);
        when(cardRepository.findAllByFeatured(isFeatured)).thenReturn(featuredCards);
        when(cardMapper.toCardDto(card1)).thenReturn(cardDto1);
        when(cardMapper.toCardDto(card2)).thenReturn(cardDto2);
        // Act
        List<CardDto> result = cardService.getFeaturedCards(isFeatured);

        //Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(cardDto1, cardDto2);

        verify(cardRepository).findAllByFeatured(isFeatured);
        verify(cardMapper).toCardDto(card1);
        verify(cardMapper).toCardDto(card2);
    }

    @Test
    void getFeaturedCards_WhenNoCardsExist_ShouldReturnEmptyList() {
        // Arrange
        boolean isFeatured = true;
        when(cardRepository.findAllByFeatured(isFeatured)).thenReturn(List.of());

        // Act
        List<CardDto> result = cardService.getFeaturedCards(isFeatured);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        verify(cardRepository).findAllByFeatured(isFeatured);
    }
}
