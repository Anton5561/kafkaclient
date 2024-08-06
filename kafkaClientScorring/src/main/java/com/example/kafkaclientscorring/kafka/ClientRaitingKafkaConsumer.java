package com.example.kafkaclientscorring.kafka;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ClientRaitingKafkaConsumer {



    String clientStatus = "MIDDLE";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ClientRaitingKafkaProducer clientRaitingKafkaProducer;




    @KafkaListener(topics = "#{'${spring.kafka.topic.push}'.split(', ')}", groupId = "group_id")
    //@KafkaListener(topics = "#{'${spring.kafka.topic.push}'.split(', ')}", groupId = "volk")

    //@KafkaListener(topics = "client_push_scorring", groupId = "group_id")
    @SneakyThrows
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("key = " + record.key());
        System.out.println("value = " + record.value());

        clientStatus = record.value().charAt(0) == 'A' ? "HIGH" : "LOW";

        System.out.println(clientStatus);
System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        clientRaitingKafkaProducer.send(record.key(), clientStatus);
System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));


    }


}
