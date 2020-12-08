package KafkaCourse;

import kafka.message.MessageAndOffset;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

// https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+SimpleConsumer+Example
// https://blog.csdn.net/suifeng3051/article/details/38657465
// use SimpleConsumer as kafka low level API
import kafka.javaapi.consumer.SimpleConsumer;

import kafka.api.FetchRequestBuilder;
import kafka.api.FetchRequest;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.message.ByteBufferMessageSet;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerLowLevelAPI {
    @SuppressWarnings("all")
    public static void main(String [] args){

        // create a simple consumer
        String host = "localhost";
        int port = 9092;
        SimpleConsumer consumer = new SimpleConsumer(host, port, 500, 10*1024, "accessLeader");

        // fetch the data
        //FetchRequest req = new FetchRequest();
        FetchRequest req = new FetchRequestBuilder().addFetch("first-topic", 1, 5, 10*1024).build();
        FetchResponse resp = consumer.fetch(req);

        resp.messageSet("first", 1);

        ByteBufferMessageSet messageSet = resp.messageSet("first", 1);

        for (MessageAndOffset messageAndOffset : messageSet) {
            ByteBuffer buffer =  messageAndOffset.message().payload();
            // TBC
        }
    }
}
