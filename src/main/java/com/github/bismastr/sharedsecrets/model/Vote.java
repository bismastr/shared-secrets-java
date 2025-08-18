package com.github.bismastr.sharedsecrets.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.bismastr.sharedsecrets.model.enums.VoteType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "answer_vote")
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    @JsonBackReference
    private Answer answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type", nullable = false, length = 10)
    private VoteType voteType;

    @Size(max = 10)
    @Column(name = "emoji", length = 10)
    private String emoji;

    @Column(name = "voted_at", nullable = false)
    private OffsetDateTime votedAt;

    @PrePersist
    public void prePersist() {
        votedAt = OffsetDateTime.now();
    }
}