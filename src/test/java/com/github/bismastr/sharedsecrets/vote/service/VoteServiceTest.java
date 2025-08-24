package com.github.bismastr.sharedsecrets.vote.service;

import com.github.bismastr.sharedsecrets.vote.dto.VoteRequestDto;
import com.github.bismastr.sharedsecrets.vote.dto.VoteResponseDto;
import com.github.bismastr.sharedsecrets.vote.mapper.VoteMapper;
import com.github.bismastr.sharedsecrets.vote.model.Vote;
import com.github.bismastr.sharedsecrets.vote.repository.VoteRepository;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
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

class VoteServiceTest {
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private VoteMapper voteMapper;
    @InjectMocks
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveVote returns VoteResponseDto when answer exists")
    void saveVoteReturnsVoteResponseDtoWhenAnswerExists() {
        UUID answerId = UUID.randomUUID();
        VoteRequestDto voteRequestDto = mock(VoteRequestDto.class);
        Vote vote = new Vote();
        Answer answer = mock(Answer.class);
        Vote savedVote = new Vote();
        VoteResponseDto voteResponseDto = mock(VoteResponseDto.class);
        when(voteRequestDto.getAnswerId()).thenReturn(answerId);
        when(voteMapper.toEntity(voteRequestDto)).thenReturn(vote);
        when(answerRepository.findById(answerId)).thenReturn(Optional.of(answer));
        when(voteRepository.save(vote)).thenReturn(savedVote);
        when(voteMapper.toDto(savedVote)).thenReturn(voteResponseDto);
        VoteResponseDto result = voteService.saveVote(voteRequestDto);
        assertThat(result).isEqualTo(voteResponseDto);
        verify(voteMapper).toEntity(voteRequestDto);
        verify(answerRepository).findById(answerId);
        verify(voteRepository).save(vote);
        verify(voteMapper).toDto(savedVote);
    }

    @Test
    @DisplayName("saveVote throws EntityNotFoundException when answer does not exist")
    void saveVoteThrowsEntityNotFoundExceptionWhenAnswerDoesNotExist() {
        UUID answerId = UUID.randomUUID();
        VoteRequestDto voteRequestDto = mock(VoteRequestDto.class);
        when(voteRequestDto.getAnswerId()).thenReturn(answerId);
        when(voteMapper.toEntity(voteRequestDto)).thenReturn(new Vote());
        when(answerRepository.findById(answerId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> voteService.saveVote(voteRequestDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Answer not found");
        verify(answerRepository).findById(answerId);
        verify(voteRepository, never()).save(any());
        verify(voteMapper, never()).toDto(any());
    }
}

