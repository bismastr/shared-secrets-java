package com.github.bismastr.sharedsecrets.answer.repository;

import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> getAnswersBycard(Card card);
}
