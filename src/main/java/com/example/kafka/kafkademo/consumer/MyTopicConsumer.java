package com.example.kafka.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTopicConsumer {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic",groupId = "kafka-sandbox")
    public void listen(String msg){
        synchronized (msg){
            messages.add(msg);
        }
    }

    public List<String> getMessages(){
        return messages;
    }
}
