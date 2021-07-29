<h1 align="center">KafkaHelloWorld</h1>
<h4 align="center">Kafka POC project that builds app & infa via various programming language for cases</h4>

<p align="center">

## INTRO
- programming language
	- [Scala](https://github.com/yennanliu/KafkaHelloWorld/tree/master/src)
	- [Java](https://github.com/yennanliu/KafkaHelloWorld/tree/master/KafkaJava), [examples](https://github.com/yennanliu/KafkaHelloWorld/tree/master/examples)
	- [Python](https://github.com/yennanliu/KafkaHelloWorld/tree/master/KafkaPython/src/main/python)

- Kafka infra build
	- [Manually](https://github.com/yennanliu/KafkaHelloWorld#Quick-Start)
	- Docker
	- [Confluent](https://github.com/yennanliu/ConfluentHelloWorld)
	- k8s
- [kafka_intro](https://github.com/yennanliu/KafkaHelloWorld/blob/master/doc/kafka_intro.md)
- [Kafka official doc](https://kafka.apache.org/documentation/#gettingStarted)
- Ref projects
	- [kafka-beginners-course](https://github.com/yennanliu/utility_Java/tree/master/kafka-beginners-course)
	- [kafka-streams-course](https://github.com/yennanliu/utility_Java/tree/master/kafka-streams-course)
	- [kafka_learning](https://github.com/yennanliu/utility_Java/tree/master/kafka_learning)

## Structure
```
├── KafkaJava   : kafka `Java` project
├── KafkaPython : kafka `Python` project
├── Makefile    : help Makefile
├── README.md  
├── build.sbt   : scala build tool (sbt) config
├── config      : kafka config (all project level)
├── data        : toy data
├── doc         : ref doc
├── docker-compose.yml  : docker yml build mini dev system (kafka + zookeeper)
├── examples    : kafka java project examples by cases
├── mk.d        : sub Makefile
├── project     : scala build tool (sbt) config
├── script      : help bash scripts
├── services    : docker yml build dev system by components
├── src         : kafka `Scala` project (source code)
```

## Todo 
- [todo](https://github.com/yennanliu/KafkaHelloWorld/blob/master/doc/todo.md)

## Prerequisites

<details>
<summary>Prerequisites</summary>

- env
	- Java JDK 1.8
	- Kafka 
	- Zookeeper
	- sbt 1.3.12 
	- Scala
	- IntelliJ

```bash
# install Java, kafka, zookeeper
brew install kafka
brew install zookeeper

# start zookeeper, kafka
brew services start zookeeper
brew services start kafka

# restart zookeeper, kafka
brew services restart zookeeper 
brew services restart kafka

# stop zookeeper, kafka
brew services stop zookeeper
brew services stop kafka
```

</details>

## Build 

<details>
<summary>Build</summary>

```bash
# sbt clean compile 
sbt compile

sbt assembly
# the build sbt jar should exist below
# /target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar
```

</details>

## Run

<details>
<summary>Run</summary>

### Qucik start - per category (Scala)

- SimpleProducerConsumer
```bash 
# producer 
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar SimpleProducerConsumer.Producer
# consumer
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar SimpleProducerConsumer.Consumer
```

- WordCount
```bash
# KafkaStream - wordcount
# create a topic
kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic text_lines
# make toy data 
echo -e "doo dooey do dodah\ndoo dooey do dodah\ndoo dooey do dodah \n 123 456 123" > data/words.txt
# run the kafkastream workcount script
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar KafkaStream.WordCount
# send it to kafka
cat data/words.txt | kafka-console-producer --broker-list localhost:9092 --topic text_lines

# check the output
kafka-console-consumer --bootstrap-server localhost:9092 \
--topic word_count_results \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true \
--property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
--property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

- ProducerConsumerPartitioner
```bash 
# create the topic
kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topic_ProducerConsumerPartitioner

# run the consumer
#java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar ProducerConsumerPartitioner.Consumer
java -cp target/scala-2.11/kafaka-app-assembly-1.0.jar com.yen.app.Consumer

# run the producer
#java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar ProducerConsumerPartitioner.Producer
java  -cp target/scala-2.11/kafaka-app-assembly-1.0.jar  com.yen.Producer.producerV2

# check the result
kafka-console-consumer --bootstrap-server localhost:9092 \
--topic topic_ProducerConsumerPartitioner \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true 
```

- AsyncProducerConsumer
```bash
# create the topic
kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topic_AsyncProducerConsumer

# run the consumer
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar AsyncProducerConsumer.ConsumerRunner

# run the producer
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar AsyncProducerConsumer.ProducerRunner

# check the result
kafka-console-consumer --bootstrap-server localhost:9092 \
--topic topic_AsyncProducerConsumer \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true 
```

</details>

## Quick-Start 

<details>
<summary>Qucik start</summary>

### Qucik start - per script (Scala)

```bash
# run per script 
# producer 1 
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Producer.KafkaProducerApp

# producer 2
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar Producer.KafkaProducerApple

# producer 3
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar Producer.KafkaProducerApp2

# producer 4
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar Producer.KafkaProducerApp3
```
```bash
# run per script 
# consumer 1
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.KafkaConsumerSubscribeApp

# consumer 2
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.ScalaConsumerExample

# consumer 3
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.KafkaConsumerApp2
```

### Qucik start manually (Scala)

```bash
# create kafka topic
kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic text_topic

# set up producer  
kafka-console-producer  --broker-list  127.0.0.1:9092 --topic text_topic --producer-property acks=all  

# set up cosumer 
kafka-console-consumer   --bootstrap-server  127.0.0.1:9092 --topic text_topic 

# sbt compile
sbt clean compile

# sbt run
sbt run

# run KafkaProducerApp : create event via kafka producer
#  [1] Consumer.KafkaConsumerSubscribeApp
#  [2] Producer.KafkaProducerApp

# run KafkaConsumerSubscribeApp : collect event via Kafka Consumer
#  [1] Consumer.KafkaConsumerSubscribeApp
#  [2] Producer.KafkaProducerApp
```

```bash
# send file as kafka stream
# run
# 1) launch consumer 
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic orders 
# 2) send stream
bash script/streamOrders.sh
```

### Qucik start manually (Python)

```bash
# install python client library 
pip3 install -r requirements.txt
# produce event 
python src/main/python/producer_demo.py
# consume event 
python src/main/python/consumer_demo.py
```

</details>

## Test 
<details>
<summary>Test</summary>

```bash
sbt test
```

</details>

## Development 

<details>
<summary>Development</summary>

- Git flow
- dev branch -> master branch
- Please create the branch as below format
	- `feature/0001-create-first-feature`
	- `fix/0001-fix-first-issue`
	- `hotfix/fix-critical-errors`
	- ...
- Step 1 
	- create branch
- Step 2
	- make a PR
- Step 3
	- merge to master

- Kafka location
	- `/usr/local/etc/kafka`

- zookeeper location
	- `/usr/local/etc/zookeeper`
	
</details>

## Ref

<details>
<summary>Ref</summary>

- Confluent kafka official tutorial
- https://kafka-tutorials.confluent.io/?utm_source=drift&utm_medium=digital&utm_campaign=ch.blog_type.community_content.kafka-tutorials

- https://sparkbyexamples.com/kafka/apache-kafka-consumer-producer-in-scala/ 
- project dependency
	- https://github.com/confluentinc/kafka-streams-examples/blob/6f24c506ca79dcf3c9695efd37a9253676176388/pom.xml

- Kafka with spark-streaming
	- https://ithelp.ithome.com.tw/articles/10188798

- Kafaka schema management
	- https://docs.confluent.io/current/schema-registry/index.html
	- https://www.madewithtea.com/posts/kafka-streams-in-scala-with-schema-registry
	
- Makefile ref 
	- https://github.com/djangodeployment/django-deployment-book/blob/master/Makefile

- Kafka Docker
	- http://wurstmeister.github.io/kafka-docker/
	- https://github.com/wurstmeister/kafka-docker
	- https://github.com/wurstmeister/kafka-docker/wiki/Connectivity
	
- Others
	- https://www.slideshare.net/ConfluentInc/reliability-guarantees-for-apache-kafka
	
</details>	
