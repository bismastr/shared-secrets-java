package com.github.bismastr.sharedsecrets.card.mapper;

import com.github.bismastr.sharedsecrets.card.dto.CardDto;
import com.github.bismastr.sharedsecrets.card.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    CardDto toDto(Card card);
    Card toEntity(CardDto cardDto);
}
