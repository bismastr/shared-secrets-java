package com.github.bismastr.sharedsecrets.repository;

import com.github.bismastr.sharedsecrets.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

}
