package com.github.bismastr.sharedsecrets.answer.repository;

import com.github.bismastr.sharedsecrets.answer.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    @Query(value = "SELECT DISTINCT a FROM Answer a LEFT JOIN FETCH a.voteCounts WHERE a.card.id = :cardId",
            countQuery = "SELECT COUNT(DISTINCT a) FROM Answer a WHERE a.card.id = :cardId")
    Page<Answer> findAllByCardIdWithVoteCounts(@Param("cardId") UUID cardId, Pageable pageable);
}
