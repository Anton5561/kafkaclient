package com.example.kafkaclientscorring.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean(value = "send_topic")
    public NewTopic topicSendClient() {
        System.out.println("СОЗДАНИЕ ТОПИКА для получения");
        return TopicBuilder.name("client_push_scorring").build();
    }

    @Bean(value =  "receive_topic")
    public NewTopic topicReciewveClient() {
        System.out.println("СОЗДАНИЕ ТОПИКА для отправки");
        return TopicBuilder.name("client_pull_scorring").build();
    }


    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.5:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);



//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());





        return new DefaultKafkaConsumerFactory<>(props);
    }





}
