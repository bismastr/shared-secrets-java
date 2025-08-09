package com.github.bismastr.sharedsecrets.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Column(nullable = false, length = 100) // Specify column constraints
    private String name;

    @NotBlank(message = "Question cannot be empty")
    @Column(nullable = false, length = 1000) // Limiting question length
    private String question;

    @NotNull(message = "Featured status must be specified")
    @Column(name = "is_featured") // Use snake_case in DB but camelCase in Java
    private boolean featured; // Java naming convention (camelCase)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
