package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.AnswerDto;
import com.github.bismastr.sharedsecrets.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Transactional
    public AnswerDto saveAnswer(AnswerDto answerDto) {
        log.debug("Saving answer: {}", answerDto);
        Answer answer = answerMapper.toEntity(answerDto);
        Answer savedAnswer = answerRepository.save(answer);
        AnswerDto savedAnswerDto = answerMapper.toDto(savedAnswer);
        log.debug("Saved answer: {}", savedAnswerDto);
        return savedAnswerDto;
    }
}
