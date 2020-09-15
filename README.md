# KafkaHelloWorld

## INTRO
- Kafka `basic op` demo via programming language
	- Scala
	- Java
	- Python
	- Go

- Kafka infra build
	- [Manually](https://github.com/yennanliu/KafkaHelloWorld#Quick-Start)
	- Docker
	- [Confluent](https://github.com/yennanliu/ConfluentHelloWorld)
	- k8s

## Todo 
- [todo.md](https://github.com/yennanliu/KafkaHelloWorld/blob/master/doc/todo.md)

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

## Quick-Start 

<details>
<summary>Qucik start</summary>

### Qucik start manually (scala)

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
kafka-console-consumer  --bootstrap-server  127.0.0.1:9092 --topic orders 
# 2) send stream
bash script/streamOrders.sh
```

### Qucik start manually (python)

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
```
```bash
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
# consumer 1
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.KafkaConsumerSubscribeApp

# consumer 2
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.ScalaConsumerExample

# consumer 3
java -cp target/scala-2.11/KafkaHelloWorld-assembly-1.0.jar   Consumer.KafkaConsumerApp2

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

</details>

## Ref

<details>
<summary>Ref</summary>

- https://sparkbyexamples.com/kafka/apache-kafka-consumer-producer-in-scala/ 
- project dependency
	- https://github.com/confluentinc/kafka-streams-examples/blob/6f24c506ca79dcf3c9695efd37a9253676176388/pom.xml

- Kafka with spark-streaming
	- https://ithelp.ithome.com.tw/articles/10188798
	
</details>	