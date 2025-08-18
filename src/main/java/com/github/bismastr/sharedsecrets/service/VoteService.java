package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.vote.VoteResponseDto;
import com.github.bismastr.sharedsecrets.dto.vote.VoteRequestDto;
import com.github.bismastr.sharedsecrets.mapper.VoteMapper;
import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.model.Vote;
import com.github.bismastr.sharedsecrets.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.repository.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final AnswerRepository answerRepository;
    private final VoteMapper voteMapper;

    @Transactional
    public VoteResponseDto saveVote(VoteRequestDto voteRequestDto) {
        log.debug("Saving vote: {}", voteRequestDto);
        Vote vote = voteMapper.toEntity(voteRequestDto);

        // Validate answer existence
        Answer answer = answerRepository.findById(voteRequestDto.getAnswerId()).orElseThrow(() -> {
            log.error("Answer with ID {} not found", voteRequestDto.getAnswerId());
            return new EntityNotFoundException("Answer not found");
        });
        vote.setAnswer(answer);

        Vote savedVote = voteRepository.save(vote);
        VoteResponseDto savedVoteResponseDto = voteMapper.toDto(savedVote);
        log.debug("Saved vote: {}", savedVoteResponseDto);
        return savedVoteResponseDto;
    }


}
