package com.github.bismastr.sharedsecrets.answer.mapper;

import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toEntity(AnswerResponseDto answerResponseDto);

    @Mapping(source = "card.id", target = "cardId")
    AnswerResponseDto toDto(Answer answer);
}