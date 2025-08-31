package com.github.bismastr.sharedsecrets.vote.repository;


import com.github.bismastr.sharedsecrets.vote.model.VoteCount;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteCountRepository extends JpaRepository<VoteCount, UUID> {
    void deleteByAnswerId(java.util.UUID answerId);
}
