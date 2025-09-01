package com.github.bismastr.sharedsecrets.vote.repository;


import com.github.bismastr.sharedsecrets.vote.model.VoteCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteCountRepository extends JpaRepository<VoteCount, UUID> {
    @Modifying
    @Query("DELETE FROM VoteCount vc WHERE vc.answer.id = :answerId")
    int deleteByAnswerId(@Param("answerId") UUID answerId);

}
