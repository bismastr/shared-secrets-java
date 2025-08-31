package com.github.bismastr.sharedsecrets.vote.model;


import com.github.bismastr.sharedsecrets.answer.model.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "vote_counts", uniqueConstraints = @UniqueConstraint(columnNames = {"answer_id", "emoticon"}))
@Getter
@Setter
public class VoteCount {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(name = "emoticon", nullable = false, length = 10)
    private String emoticon;

    @Column(name = "count", nullable = false)
    private Long count;
}
