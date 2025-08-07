package com.github.bismastr.sharedsecrets.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String question;

    @NotNull
    @Column(nullable = false)
    private boolean is_featured;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions='" + question +
                ", createdAt=" + is_featured +
                ", updatedAt=" + created_at +
                ", expiresAt=" + updated_at +
                '}';
    }
}
