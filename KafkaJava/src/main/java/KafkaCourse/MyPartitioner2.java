package KafkaCourse;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartitioner2 implements Partitioner{
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster){

        // use user defined algorithm
        // hash & %
        String val = (String) value;
        int result = value.hashCode() ^ Integer.MAX_VALUE;
        return result % 4 ; // if 4 clusters
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs){

    }
}