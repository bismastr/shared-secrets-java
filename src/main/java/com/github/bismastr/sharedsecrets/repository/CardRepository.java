package com.github.bismastr.sharedsecrets.repository;

import com.github.bismastr.sharedsecrets.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    @Query("SELECT c FROM Card c WHERE c.featured = ?1")
    List<Card> findAllByFeatured(boolean isFeatured);
}
