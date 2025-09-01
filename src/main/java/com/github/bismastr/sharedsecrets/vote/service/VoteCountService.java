package com.github.bismastr.sharedsecrets.vote.service;


import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.vote.model.Vote;
import com.github.bismastr.sharedsecrets.vote.model.VoteCount;
import com.github.bismastr.sharedsecrets.vote.repository.VoteCountRepository;
import com.github.bismastr.sharedsecrets.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteCountService {
    private final VoteRepository voteRepository;
    private final VoteCountRepository voteCountRepository;
    private final AnswerRepository answerRepository;
    private final Map<UUID, Object> answerLocks = new ConcurrentHashMap<>();

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void processVote(UUID answerId) {
        Object lock = answerLocks.computeIfAbsent(answerId, k -> new Object());
        synchronized (lock) {
            try {
                Answer answer = answerRepository.findById(answerId).orElse(null);
                if (answer == null) {
                    log.warn("Answer not found for ID: {}", answerId);
                    return;
                }

                List<Vote> vote = voteRepository.findByAnswerId(answer.getId());
                Map<String, Long> voteCountMap = new HashMap<>(vote.size());
                for (Vote v : vote) {
                    String emoticon = (v.getEmoji() == null) ? "❤️" : v.getEmoji();
                    voteCountMap.put(emoticon, voteCountMap.getOrDefault(emoticon, 0L) + 1);
                }

                deleteVoteCountsByAnswerId(answerId);

                List<VoteCount> newCounts = new ArrayList<>();
                for (Map.Entry<String, Long> entry : voteCountMap.entrySet()) {
                    VoteCount vc = new VoteCount();
                    vc.setAnswer(answer);
                    vc.setEmoticon(entry.getKey());
                    vc.setCount(entry.getValue());
                    newCounts.add(vc);
                }

                voteCountRepository.saveAll(newCounts);
                log.info("Aggregated {} vote(s) for answer {}", newCounts.size(), answerId);
            } finally {
                answerLocks.remove(answerId);
            }
        }
    }

    @Transactional
    public void deleteVoteCountsByAnswerId(UUID answerId) {
        try {
            int counts = voteCountRepository.deleteByAnswerId(answerId);
            log.info("Deleted {} vote counts for answerId: {}", counts, answerId);
        } catch (Exception e) {
            log.error("Error deleting vote counts for answer {}: {}", answerId, e.getMessage(), e);
            throw e;
        }

    }
}
