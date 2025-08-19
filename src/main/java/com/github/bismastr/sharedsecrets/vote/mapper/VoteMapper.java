package com.github.bismastr.sharedsecrets.vote.mapper;

import com.github.bismastr.sharedsecrets.vote.dto.VoteRequestDto;
import com.github.bismastr.sharedsecrets.vote.dto.VoteResponseDto;
import com.github.bismastr.sharedsecrets.vote.model.Vote;
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
