package com.github.bismastr.sharedsecrets.service;


import com.github.bismastr.sharedsecrets.dto.answer.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.repository.CardRepository;
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
    private final CardRepository cardRepository;

    @Transactional
    public AnswerResponseDto saveAnswer(AnswerResponseDto answerResponseDto) {
        log.debug("Saving answer: {}", answerResponseDto);
        Answer answer = answerMapper.toEntity(answerResponseDto);

        cardRepository.findById(answerResponseDto.getCardId())
                .orElseThrow(() -> new IllegalArgumentException("Card not found with ID: " + answerResponseDto.getCardId()));
        answer.setCard(cardRepository.getReferenceById(answerResponseDto.getCardId()));
        Answer savedAnswer = answerRepository.save(answer);

        AnswerResponseDto savedAnswerResponseDto = answerMapper.toDto(savedAnswer);
        log.debug("Saved answer: {}", savedAnswerResponseDto);
        return savedAnswerResponseDto;
    }
}
