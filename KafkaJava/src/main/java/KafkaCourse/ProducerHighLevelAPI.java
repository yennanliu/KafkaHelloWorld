package KafkaCourse;

// https://www.youtube.com/watch?v=FybZ9gMFytE&list=PLmOn9nNkQxJEDjzl0iBYZ3WuXUuUStxZl&index=10
// create a producer via kafka Java high level API

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerHighLevelAPI {
    public static void main(String[] args){
        System.out.println("ProducerHighLevelAPI run ...");

        // 0) Config
        // good to check the default config : /kafka/config/producer.Properties
        Properties props = new Properties();

        // kafka server name and port
        // could be "hadoop1:9092, hadoop2:9092, ...." if is a cluster
        props.setProperty("bootstrap.servers", "localhost:9092");

        // serialize the key, value object (msg) for better transmission performance (optional)
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // wait till all followers' (copy) responses (optional)
        // could be ["all", -1, 0 , 1]
        props.put("acks", "1");

        // max retry when sending msg (optional)
        props.put("retries", 0);

        // size of one batch of msg (optional)
        props.put("batch.size", 16384);

        // 1) Create producer
        // Producer<key, value> -> the msg will be in the <key, value > format
        // while key is relative to the msg partition
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        // 2) Prepare the data (msg)
        String topic = "first_";
        String value = "hello kafka !!!!!!";
        ProducerRecord record = new ProducerRecord(topic, value);

        // 3) Send the msg
        producer.send(record);

        // close the producer
        producer.close();
    }
}
