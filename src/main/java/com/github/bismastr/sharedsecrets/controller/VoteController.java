package com.github.bismastr.sharedsecrets.controller;


import com.github.bismastr.sharedsecrets.dto.vote.VoteResponseDto;
import com.github.bismastr.sharedsecrets.dto.vote.VoteRequestDto;
import com.github.bismastr.sharedsecrets.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<VoteResponseDto> saveVote(@RequestBody VoteRequestDto voteDto) {
        VoteResponseDto voted = voteService.saveVote(voteDto);
        return ResponseEntity.ok(voted);
    }
}
