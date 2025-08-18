package com.github.bismastr.sharedsecrets.mapper;

import com.github.bismastr.sharedsecrets.dto.vote.VoteRequestDto;
import com.github.bismastr.sharedsecrets.dto.vote.VoteResponseDto;
import com.github.bismastr.sharedsecrets.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {
    @Mapping(target = "answerId", source = "answer.id")
    VoteResponseDto toDto(Vote vote);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "votedAt", ignore = true)
    @Mapping(target = "answer", ignore = true)
    Vote toEntity(VoteRequestDto voteRequestDto);
}
