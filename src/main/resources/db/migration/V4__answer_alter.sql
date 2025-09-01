ALTER TABLE answer
    ALTER COLUMN answer_text TYPE VARCHAR(255) USING (answer_text::VARCHAR(255));