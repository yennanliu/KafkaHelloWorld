### Notice
- This project can also be run via
	- basic kafka
	- kafka stream
	- KsqlDB

### Ref
- https://kafka-tutorials.confluent.io/create-stateful-aggregation-count/kstreams.html

### Quick Start
```bash
# run docker
git clone https://github.com/yennanliu/KafkaHelloWorld.git
cd KafkaHelloWorld/examples/count_a_stream_of_events
docker-compose up -d


mkdir -p src/main/avro

# compile 
./gradlew build
```