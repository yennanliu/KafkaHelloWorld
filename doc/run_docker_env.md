### Steps Run Services Via Docker Images

### Steps
```bash
# run docker
git clone https://github.com/yennanliu/KafkaHelloWorld.git
cd KafkaHelloWorld/dockerfiles
docker-compose up -d

# ksql
docker exec -it ksqldb-cli ksql http://ksqldb-server:8088

# kafka
docker exec -it broker bash

# zookeeper
docker exec -it zookeeper bash
```