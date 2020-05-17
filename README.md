# KafkaHelloWorld

## INTRO
- Demo Kafka `basic op` via client side API (Scala/Python)
	- Kafka producer
	- Kafka consumer
	- Kafka broker
	- Kafka group
	- Kafka topic
	- Kafka shift

- Kafka cluster setup
	- dev

## Prerequisites

```bash
# install Java, kafka, zookeeper

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

## Qucik start (Scala)
```bash
# create kafka topic
kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic text_topic

# sbt compile
sbt clean compile

```


## Quick start (Python)

```bash
# install python client library 
pip3 install -r requirements.txt
# produce event 
python python/producer_demo.py
# consume event 
python python/consumer_demo.py
```

## Ref
- https://sparkbyexamples.com/kafka/apache-kafka-consumer-producer-in-scala/ 
