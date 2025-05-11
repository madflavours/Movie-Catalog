package com.scroll.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.scroll.pojo.MovieUpdateEvent;

@Service
public class MovieUpdateEventConsumer {

    @KafkaListener(topics = "movie-update-events", groupId = "test-group")
    public void consume(MovieUpdateEvent event) {
        System.out.println("🎬 Movie update event consumed:");
        System.out.println("Before: " + event.getBefore());
        System.out.println("After: " + event.getAfter());
    }
}
