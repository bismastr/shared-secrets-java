package com.github.bismastr.sharedsecrets.controller;


import com.github.bismastr.sharedsecrets.dto.VoteDto;
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
    public ResponseEntity<VoteDto> saveVote(@RequestBody VoteDto voteDto) {
        VoteDto voted = voteService.saveVote(voteDto);
        return ResponseEntity.ok(voted);
    }
}
