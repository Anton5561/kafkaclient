package com.example.kafkaclientscorring.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRaitingKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.pull}")
    private String topic_pull;

    public void send(String key, String value) throws Exception {

        //SendResult<String, String> stringStrSendResult =
              //kafkaTemplate.send("#{'${spring.kafka.topic.pull}'.split(', ')}", key, value)
        //kafkaTemplate.send("client_pull_scorring", key, value)
        kafkaTemplate.send(topic_pull, key, value)
                .get();
    }

}
