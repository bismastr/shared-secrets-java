package com.github.bismastr.sharedsecrets.repository;

import com.github.bismastr.sharedsecrets.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CardRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        cardRepository.deleteAll();
    }

    @Test
    void findAllByFeatured_WhenFeaturedCards_ShouldReturnFeaturedCards() {
        Card featuredCard1 = createCard("Featured Card 1", "Question 1", true);
        Card featuredCard2 = createCard("Featured Card 2", "Question 2", true);
        Card nonFeaturedCard = createCard("Non-featured Card", "Question 3", false);

        entityManager.persist(featuredCard1);
        entityManager.persist(featuredCard2);
        entityManager.persist(nonFeaturedCard);
        entityManager.flush();

        List<Card> featuredCards = cardRepository.findAllByFeatured(true);

        assertThat(featuredCards).hasSize(2);
        assertThat(featuredCards)
                .extracting(Card::getQuestion)
                .containsExactlyInAnyOrder("Question 1", "Question 2");
    }

    private Card createCard(String name, String question, boolean isFeatured) {
        Card card = new Card();
        card.setQuestion(question);
        card.setFeatured(isFeatured);
        card.setUpdatedAt(LocalDateTime.now());
        return card;
    }
}