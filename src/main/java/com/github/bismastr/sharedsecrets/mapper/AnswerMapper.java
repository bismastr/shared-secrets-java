package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.dto.AnswerDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toEntity(AnswerDto answerDto);

    @AfterMapping
    default void linkAnswerVotes(@MappingTarget Answer answer) {
        answer.getAnswerVotes().forEach(answerVote -> answerVote.setAnswer(answer));
    }

    AnswerDto toDto(Answer answer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Answer partialUpdate(AnswerDto answerDto, @MappingTarget Answer answer);
}