package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.dto.answer.AnswerResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toEntity(AnswerResponseDto answerResponseDto);
    @Mapping(source = "card.id", target = "cardId")
    AnswerResponseDto toDto(Answer answer);
}