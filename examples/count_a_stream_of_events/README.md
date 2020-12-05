### Notice
- This project can also be run via
	- basic kafka
	- kafka stream

### Ref
- https://kafka-tutorials.confluent.io/create-stateful-aggregation-count/kstreams.html

### Quick Start
```bash
# run docker
git clone https://github.com/yennanliu/KafkaHelloWorld.git
cd KafkaHelloWorld/examples/count_a_stream_of_events
docker-compose up -d

# into the kafka broker env
docker exec -it broker bash 

gradle wrapper

mkdir -p src/main/avro

# compile 
./gradlew build
```