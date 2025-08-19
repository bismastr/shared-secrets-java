package com.github.bismastr.sharedsecrets.answer.repository;

import com.github.bismastr.sharedsecrets.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

}
