package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.dto.VoteDto;
import com.github.bismastr.sharedsecrets.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {
    VoteDto toDto(Vote vote);
    Vote toEntity(VoteDto voteDto);
}
