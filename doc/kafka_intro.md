## Kafka Intro

### Purpose
- Kafka is a "message collection center" that ingest/process/outprocess serves multiple components

### Basic Moods 
- Pub-Sub: `publish/subscribe`. Kafka collect and save data on different `topics`, so the consumer can "subscribe" and process them per their needs. --> `The main mood Kafka is running for`
- Consumer pull: consumer(e.g. server/API/backend) pull the data by itself. The data will be `gone` once it is consumed

### Basic Architecture
- Zookeeper: "registration center", coordinates kafka and consumers. Consumers know which topics/partitions/offset with data they are requesting via zookeeper first, then query  kafka 
```
Producer1------->                       -------> Consumer1
Producer2------->  Kafka <--> Zookeeper -------> Consumer2
...      ------->                       -------> ...
ProducerN------->                       -------> ConsumerN       
```

### Terms
- Broker: `Kafka`, the main component collect/process data
- Producer: data source, can be DB/Backend/Other Kafka/API..
- consumer: components that query data from kafka, can be Backend/Other Kafka/API..
- `Offset`: the `*order*` of data in 1 broker, it's critical to have a way to manage the ordering of message (data) within Kfaka. 
- Topic : the area that Kafka save data, so the consumers know to which topic they can get the data rather than always scan on whole kafka
- Partition: In case there are too much data in one topic, we can set up a partition to distribute data with the same topics but in different sub groups (partition)
