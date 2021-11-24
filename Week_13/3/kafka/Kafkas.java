package com.myproject.moods.distribute.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-07
 */
@Controller
@SpringBootApplication
public class Kafkas {
        @Autowired
        private  KafkaTemplate kafkaTemplate;


        private  void send(String key, String data){
                kafkaTemplate.send("test",key,data);
        }
        @GetMapping("/test")
        public String test(){
                for (int i = 0; i <5 ; i++) {
                        send("key"+i,"data"+i);
                }
                return  "success";
        }
        @KafkaListener(topics = "test")
        private void consumer(ConsumerRecord<?,?> consumerRecord){
                System.out.println("topic"+consumerRecord.topic());
                System.out.println("key"+consumerRecord.key());
                System.out.println("data"+consumerRecord.value());
        }

        public static void main(String[] args) {
                SpringApplication.run(Kafkas.class,args);
        }
}
