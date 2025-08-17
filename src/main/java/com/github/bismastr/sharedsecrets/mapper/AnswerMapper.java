package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.model.Answer;
import com.github.bismastr.sharedsecrets.dto.AnswerDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toEntity(AnswerDto answerDto);
    AnswerDto toDto(Answer answer);
}