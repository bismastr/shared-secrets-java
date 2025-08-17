package com.github.bismastr.sharedsecrets.service;

import com.github.bismastr.sharedsecrets.dto.AnswerDto;
import com.github.bismastr.sharedsecrets.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AnswerMapper answerMapper;

    @InjectMocks
    private AnswerService answerService;

    @Test
    void saveAnswer_shouldSaveAndReturnMappedDto() {
        AnswerDto answerDtoToSave = AnswerDto.builder()
                .answerText("This is a test answer.")
                .build();

        Answer answerToSave = new Answer();
        answerToSave.setAnswerText("This is a test answer.");

        Answer savedAnswer = new Answer();
        savedAnswer.setId(UUID.randomUUID());
        savedAnswer.setCreatedAt(OffsetDateTime.now());
        savedAnswer.setUpdatedAt(OffsetDateTime.now());

        AnswerDto expectedDto = AnswerDto.builder()
            .id(savedAnswer.getId())
            .answerText(savedAnswer.getAnswerText())
            .createdAt(savedAnswer.getCreatedAt())
            .updatedAt(savedAnswer.getUpdatedAt())
            .build();

        when(answerMapper.toEntity(any(AnswerDto.class))).thenReturn(answerToSave);
        when(answerRepository.save(any(Answer.class))).thenReturn(savedAnswer);
        when(answerMapper.toDto(any(Answer.class))).thenReturn(expectedDto);

        AnswerDto result = answerService.saveAnswer(answerDtoToSave);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedDto.getId());
        assertThat(result.getAnswerText()).isEqualTo(expectedDto.getAnswerText());

        verify(answerMapper).toEntity(answerDtoToSave);
        verify(answerRepository).save(answerToSave);
        verify(answerMapper).toDto(savedAnswer);
    }
}