package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.dto.CardDto;
import com.github.bismastr.sharedsecrets.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    CardDto toDto(Card card);
    Card toEntity(CardDto cardDto);
}
