package com.github.bismastr.sharedsecrets.vote.repository;

import com.github.bismastr.sharedsecrets.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
    List<Vote> findByAnswerId(UUID answerId);
}
