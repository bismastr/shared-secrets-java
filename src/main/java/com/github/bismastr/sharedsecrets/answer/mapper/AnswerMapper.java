package com.github.bismastr.sharedsecrets.answer.mapper;

import com.github.bismastr.sharedsecrets.answer.dto.GetAnswersByCardIdResponse;
import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.dto.AnswerResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toEntity(AnswerResponseDto answerResponseDto);
    @Mapping(source = "card.id", target = "cardId")
    AnswerResponseDto toDto(Answer answer);


    GetAnswersByCardIdResponse.AnswerByCardId toAnswerByCardId(Answer answer);
}