package com.example.kafka.kafkademo.api;

import com.example.kafka.kafkademo.consumer.MyTopicConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaApi {

    private KafkaTemplate<String,String> template;
    private MyTopicConsumer myTopicConsumer;

    public KafkaApi(KafkaTemplate<String,String> template,MyTopicConsumer myTopicConsumer){
        this.template=template;
        this.myTopicConsumer=myTopicConsumer;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produce(@RequestParam String msg){
        template.send("myTopic",msg);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/consume")
    public ResponseEntity<List<String>> consume(){
        return ResponseEntity.ok(myTopicConsumer.getMessages());
    }

}
