package com.github.bismastr.sharedsecrets.vote.service;


import com.github.bismastr.sharedsecrets.vote.dto.VoteResponseDto;
import com.github.bismastr.sharedsecrets.vote.dto.VoteRequestDto;
import com.github.bismastr.sharedsecrets.vote.mapper.VoteMapper;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.vote.model.Vote;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.vote.repository.VoteRepository;
import com.github.bismastr.sharedsecrets.vote.worker.VoteAggregationWorker;
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
    private final VoteAggregationWorker voteAggregationWorker;

    @Transactional
    public VoteResponseDto saveVote(VoteRequestDto voteRequestDto) {
        log.debug("Saving vote: {}", voteRequestDto);
        Vote vote = voteMapper.toEntity(voteRequestDto);

        Answer answer = answerRepository.findById(voteRequestDto.getAnswerId()).orElseThrow(() -> {
            log.error("Answer with ID {} not found", voteRequestDto.getAnswerId());
            return new EntityNotFoundException("Answer not found");
        });
        vote.setAnswer(answer);

        Vote savedVote = voteRepository.save(vote);
        VoteResponseDto savedVoteResponseDto = voteMapper.toDto(savedVote);
        log.debug("Saved vote: {}", savedVoteResponseDto);

        voteAggregationWorker.submitForAggregation(answer.getId());
        return savedVoteResponseDto;
    }


}
