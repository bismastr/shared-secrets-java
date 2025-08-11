package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.dto.CardDto;
import com.github.bismastr.sharedsecrets.model.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto toCardDto(Card card);
    Card toCard(CardDto cardDto);
}
