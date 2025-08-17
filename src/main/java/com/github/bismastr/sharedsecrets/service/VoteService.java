package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.VoteDto;
import com.github.bismastr.sharedsecrets.mapper.VoteMapper;
import com.github.bismastr.sharedsecrets.model.Vote;
import com.github.bismastr.sharedsecrets.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Transactional
    public VoteDto saveVote(VoteDto voteDto) {
        log.debug("Saving vote: {}", voteDto);
        Vote vote = voteMapper.toEntity(voteDto);
        Vote savedVote = voteRepository.save(vote);
        VoteDto savedVoteDto = voteMapper.toDto(savedVote);
        log.debug("Saved vote: {}", savedVoteDto);
        return savedVoteDto;
    }


}
