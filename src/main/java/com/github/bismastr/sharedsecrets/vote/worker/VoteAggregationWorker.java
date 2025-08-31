package com.github.bismastr.sharedsecrets.vote.worker;

import com.github.bismastr.sharedsecrets.answer.model.Answer;
import com.github.bismastr.sharedsecrets.answer.repository.AnswerRepository;
import com.github.bismastr.sharedsecrets.vote.model.Vote;
import com.github.bismastr.sharedsecrets.vote.model.VoteCount;
import com.github.bismastr.sharedsecrets.vote.repository.VoteCountRepository;
import com.github.bismastr.sharedsecrets.vote.repository.VoteRepository;
import com.github.bismastr.sharedsecrets.vote.service.VoteCountService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class VoteAggregationWorker {
    private final VoteCountService voteCountService;

    private final BlockingQueue<UUID> aggregationQueue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void startWorker() {
        Thread workerThread = new Thread(() -> {
            while (true) {
                try {
                    UUID answerId = aggregationQueue.take();
                    voteCountService.processVote(answerId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.warn("Vote aggregation worker interrupted", e);
                    break;
                } catch (Exception e) {
                    log.error("Error during vote aggregation", e);
                }
            }
        });
        workerThread.setDaemon(true);
        workerThread.start();
    }

    public void submitForAggregation(UUID answerId) {
        if (!aggregationQueue.contains(answerId)) {
            boolean offered = aggregationQueue.offer(answerId);
            if (offered) {
                log.debug("Added answer {} to aggregation queue.", answerId);
            } else {
                log.warn("Aggregation queue is full. Failed to add answer {} to queue.", answerId);
            }
        }
    }
}
