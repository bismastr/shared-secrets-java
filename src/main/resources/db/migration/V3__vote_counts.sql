CREATE TABLE vote_counts
(
    id        UUID        NOT NULL,
    answer_id UUID        NOT NULL,
    emoticon  VARCHAR(10) NOT NULL,
    count     BIGINT      NOT NULL,
    CONSTRAINT pk_vote_counts PRIMARY KEY (id)
);

ALTER TABLE vote_counts
    ADD CONSTRAINT uc_b0cb61470c2807a4d96a232bc UNIQUE (answer_id, emoticon);

ALTER TABLE vote_counts
    ADD CONSTRAINT FK_VOTE_COUNTS_ON_ANSWER FOREIGN KEY (answer_id) REFERENCES answer (id);