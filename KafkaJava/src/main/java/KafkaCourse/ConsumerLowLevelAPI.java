package KafkaCourse;

import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataResponse;
import kafka.javaapi.TopicMetadataRequest;
import kafka.message.MessageAndOffset;

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

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ConsumerLowLevelAPI {
    @SuppressWarnings("all")
    public static void main(String [] args) throws Exception {

        // init values
        BrokerEndPoint leader = null;

        // create a simple consumer
        String host = "localhost";
        int port = 9092;

        /*
         PART 1 : GET METADATA INFORM (leader, partition...)
         */
        // get partiiton leader
        SimpleConsumer metaConsumer = new SimpleConsumer(host, port, 500,
                10*1024, "metaData");

        // get metaData information
        TopicMetadataRequest request = new TopicMetadataRequest(Arrays.asList("first"));
        TopicMetadataResponse response =  metaConsumer.send(request);

        dropOut:
        for (TopicMetadata topicMetadata : response.topicsMetadata()) {
            // go through topic, and filtet topic == first
            if ("first".equals(topicMetadata.topic())){
                // go through partition, and get partition == 1
                for (PartitionMetadata partitionMetadata: topicMetadata.partitionsMetadata()) {
                    int partid = partitionMetadata.partitionId();
                    if (partid == 1){
                        leader = partitionMetadata.leader();
                        System.out.println("*** leader = " + leader);
                        break dropOut;  // if found, break out the loop directly
                    }
                }
            }
        }

        // if can not get the correct meta (leader, partition) information, will not run the application
        if (leader == null) {
            System.out.println("Partition information not correct. Abort the process");
            return;
        }

        /*
         PART 2 : LOGIC RUN WITH LDADER
         */
        // host, port should be the leader in such partition
        SimpleConsumer consumer = new SimpleConsumer(leader.host(), leader.port(), 500,
                10*1024, "accessLeader");

        // fetch the data
        //FetchRequest req = new FetchRequest();
        FetchRequest req = new FetchRequestBuilder().addFetch("first-topic", 1, 5, 10*1024).build();
        FetchResponse resp = consumer.fetch(req);

        resp.messageSet("first", 1);

        ByteBufferMessageSet messageSet = resp.messageSet("first", 1);

        for (MessageAndOffset messageAndOffset : messageSet) {
            ByteBuffer buffer =  messageAndOffset.message().payload();
            byte[] bs = new byte[buffer.limit()];
            buffer.get(bs);
            String value = new String(bs, "UTF-8");
            System.out.println(value);
        }
    }
}
