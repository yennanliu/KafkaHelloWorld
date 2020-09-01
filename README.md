# KafkaHelloWorld

## INTRO
- Kafka `basic op` demo via programming language
	- Scala
	- Java
	- Python

- Kafka cluster setup
	- Manually
	- Docker
	- [Confluent](https://github.com/yennanliu/ConfluentHelloWorld)
	- k8s

## Todo 

<details>
<summary>Todo</summary>

- Infra
- Kafka clusters infra code
- Kafka producer
- Kafka consumer
- Kafka broker
- Kafka group
- Kafka topic
- Kafka shift
- Kafka - spark
- Kafka - DB
- kafka-schema-registry

</details>

## Prerequisites

<details>
<summary>Prerequisites</summary>

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

## Qucik start

<details>
<summary>Qucik start</summary>

### Qucik start (scala)

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

### Qucik start (python)

```bash
# install python client library 
pip3 install -r requirements.txt
# produce event 
python python/producer_demo.py
# consume event 
python python/consumer_demo.py
```

</details>

## Build 
<details>
<summary>Build</summary>

```bash 
sbt assembly
# [info] Run completed in 31 milliseconds.
# [info] Total number of tests run: 0
# [info] Suites: completed 0, aborted 0
# [info] Tests: succeeded 0, failed 0, canceled 0, ignored 0, pending 0
# [info] No tests were executed.
# [info] Strategy 'discard' was applied to 3 files (Run the task at debug level to see details)
# [info] Assembly up to date: /Users/yennanliu/KafkaHelloWorld/target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar
# [success] Total time: 1 s, completed S

# run the app via compiled jar
# app 1 
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Producer.KafkaProducerApp
# app 2 
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.KafkaConsumerSubscribeApp

```
</details>

## Ref

<details>
<summary>Ref</summary>

- https://sparkbyexamples.com/kafka/apache-kafka-consumer-producer-in-scala/ 
- project dependency
	- https://github.com/confluentinc/kafka-streams-examples/blob/6f24c506ca79dcf3c9695efd37a9253676176388/pom.xml
	
</details>	