package com.github.bismastr.sharedsecrets.service;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.service.AnswerService;
import com.github.bismastr.sharedsecrets.card.model.Card;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AnswerMapper answerMapper;

    @InjectMocks
    private AnswerService answerService;

    @Test
    void saveAnswer_shouldSaveAndReturnMappedDto() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card();
        card.setId(cardId);

        AnswerResponseDto answerResponseDtoToSave = AnswerResponseDto.builder()
                .cardId(cardId)
                .answerText("This is a test answer.")
                .build();

        Answer answerToSave = new Answer();
        answerToSave.setAnswerText("This is a test answer.");

        Answer savedAnswer = new Answer();
        savedAnswer.setId(UUID.randomUUID());
        savedAnswer.setCard(card);
        savedAnswer.setAnswerText("This is a test answer.");
        savedAnswer.setCreatedAt(OffsetDateTime.now());
        savedAnswer.setUpdatedAt(OffsetDateTime.now());

        AnswerResponseDto expectedDto = AnswerResponseDto.builder()
                .id(savedAnswer.getId())
                .cardId(cardId)
                .answerText(savedAnswer.getAnswerText())
                .createdAt(savedAnswer.getCreatedAt())
                .updatedAt(savedAnswer.getUpdatedAt())
                .build();

        AnswerResponseDto result = answerService.saveAnswer(answerResponseDtoToSave);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedDto.getId());
        assertThat(result.getCardId()).isEqualTo(cardId);
        assertThat(result.getAnswerText()).isEqualTo(expectedDto.getAnswerText());
    }

    @Test
    void saveAnswer_shouldThrowException_whenCardNotFound() {
        UUID cardId = UUID.randomUUID();
        AnswerResponseDto answerResponseDtoToSave = AnswerResponseDto.builder()
                .cardId(cardId)
                .answerText("This answer won't be saved.")
                .build();

        when(cardRepository.findById(cardId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> answerService.saveAnswer(answerResponseDtoToSave))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Card not found with id: " + cardId);
    }
}