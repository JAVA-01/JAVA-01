package com.myproject.moods.distribute.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.*;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-07
 */
public class MengXiangYingProduce implements Produce {

    private Properties properties;
    private KafkaProducer<?,?> producer;
    private final String topic = "order-test1";
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private Set<String> orderSet = new HashSet<>();
    private volatile boolean flag = true;
    public MengXiangYingProduce() {

        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
//        properties.put("queue.enqueue.timeout.ms", -1);
//        properties.put("enable.idempotence", true);
//        properties.put("transactional.id", "transactional_1");
//        properties.put("acks", "all");
//        properties.put("retries", "3");
//        properties.put("max.in.flight.requests.per.connection", 1);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
        //producer.initTransactions();
    }

    @Override
    public void send() {
        producer.beginTransaction();

        try {
            for (int i = 0; i < 5; i++) {
                ProducerRecord record = new ProducerRecord(topic,"key"+i,"data"+i);
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.println(recordMetadata);
                    }
                });
            }

            producer.commitTransaction();

        } catch (Throwable e) {
            //producer.abortTransaction();
        }
        //System.out.println("************" + json + "************");
    }
}
