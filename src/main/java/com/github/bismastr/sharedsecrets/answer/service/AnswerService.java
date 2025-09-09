package com.github.bismastr.sharedsecrets.answer.service;


import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.mapper.AnswerMapper;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.card.model.Card;
import com.github.bismastr.sharedsecrets.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + answerResponseDto.getCardId()));
        answer.setCard(cardRepository.getReferenceById(answerResponseDto.getCardId()));
        Answer savedAnswer = answerRepository.save(answer);

        AnswerResponseDto savedAnswerResponseDto = answerMapper.toDto(savedAnswer);
        log.debug("Saved answer: {}", savedAnswerResponseDto);
        return savedAnswerResponseDto;
    }

    @Transactional(readOnly = true)
    public List<AnswerResponseDto> findAllByCardId(UUID cardId, Pageable pageable) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + cardId));

        Page<Answer> answerPage = answerRepository.findAllByCardIdWithVoteCounts(card.getId(), pageable);
        return answerMapper.toDtoList(answerPage.getContent());
    }
}
