### KafkaHelloWorld - Java

### Prerequisite
1. Install java8
2. install gradle : ` brew install gradle`

### Steps
1. build the jar : `gradle shadowJar`
2. run the java application : `java -jar build/libs/message-ordering-standalone-0.0.1.jar configuration/dev.properties data/input.txt`
3. check the result:
```bash
kafka-console-consumer --bootstrap-server localhost:9092 \
--topic myTopic \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true 
```


### Ref
- https://kafka-tutorials.confluent.io/