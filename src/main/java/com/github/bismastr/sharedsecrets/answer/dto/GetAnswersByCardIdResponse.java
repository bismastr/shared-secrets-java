package com.github.bismastr.sharedsecrets.answer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GetAnswersByCardIdResponse {
    String cardId;
    List<AnswerByCardId> answers;

    @Data
    public static class AnswerByCardId {
        UUID id;
        String answerText;
        OffsetDateTime createdAt;
        List<VoteByCardId> votes;
    }

    @Data
    public static class VoteByCardId {
        String emoticon;
        Long count;
    }
}
