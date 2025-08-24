package com.github.bismastr.sharedsecrets.answer.service;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.card.model.Card;
import com.github.bismastr.sharedsecrets.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnswerServiceTest {
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private AnswerMapper answerMapper;
    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveAnswer returns saved AnswerResponseDto when card exists")
    void saveAnswerReturnsSavedAnswerResponseDtoWhenCardExists() {
        UUID cardId = UUID.randomUUID();
        AnswerResponseDto inputDto = mock(AnswerResponseDto.class);
        Answer answer = new Answer();
        Answer savedAnswer = new Answer();
        AnswerResponseDto savedDto = mock(AnswerResponseDto.class);
        Card card = mock(Card.class);
        when(inputDto.getCardId()).thenReturn(cardId);
        when(answerMapper.toEntity(inputDto)).thenReturn(answer);
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardRepository.getReferenceById(cardId)).thenReturn(card);
        when(answerRepository.save(answer)).thenReturn(savedAnswer);
        when(answerMapper.toDto(savedAnswer)).thenReturn(savedDto);
        AnswerResponseDto result = answerService.saveAnswer(inputDto);
        assertThat(result).isEqualTo(savedDto);
        verify(cardRepository).findById(cardId);
        verify(cardRepository).getReferenceById(cardId);
        verify(answerRepository).save(answer);
        verify(answerMapper).toDto(savedAnswer);
    }

    @Test
    @DisplayName("saveAnswer throws EntityNotFoundException when card does not exist")
    void saveAnswerThrowsEntityNotFoundExceptionWhenCardDoesNotExist() {
        UUID cardId = UUID.randomUUID();
        AnswerResponseDto inputDto = mock(AnswerResponseDto.class);
        when(inputDto.getCardId()).thenReturn(cardId);
        when(answerMapper.toEntity(inputDto)).thenReturn(new Answer());
        when(cardRepository.findById(cardId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> answerService.saveAnswer(inputDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(cardId.toString());
        verify(cardRepository).findById(cardId);
        verify(cardRepository, never()).getReferenceById(any());
        verify(answerRepository, never()).save(any());
    }
}
