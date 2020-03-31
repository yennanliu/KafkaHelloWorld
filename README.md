# KafkaHelloWorld

## INTRO
- Kafka `basic setup and simple op` demo via Scala/Python
	- Kafka producer
	- Kafka consumer
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

## Quick start (python)

```bash
# install python client library 
pip3 install -r requirements.txt

# produce event 
python python/simple_producer.py

# consume event 
python python/simple_consumer.py

```

## Ref
- dev 
