package KafkaCourse;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerHighLevelAPI {
    public static void main(String [] args){

        // 0) Config
        // good to check the default config : /kafka/config/producer.Properties
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");

        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.setProperty("group.id", "my_group_1");

        // if true the consumer's offset will be periodically committed in the background
        props.setProperty("enable.auto.commit", "true");

        // the freq in milliseconds that consumer offsets are auto-committed to kafka
        props.setProperty("auto.commit.interval.ms", "1000");

        // create consumer object
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // subscribe topics
        consumer.subscribe(Arrays.asList("first"));

        // get the msg (from producer)
        while (true){

            ConsumerRecords<String, String> records =  consumer.poll(500);

            // can do below for loop since ConsumerRecords implements "Iterable" interface
            for (ConsumerRecord<String, String> record: records ){
                System.out.println(record);
            }
        }
    }
}
