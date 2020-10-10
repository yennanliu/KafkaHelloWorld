help:
	@echo "Please use \`make <target>' is one of"
	@echo "  compile    run sbt compile"
	@echo "  run        run sbt run"
	@echo "  test       run sbt test"
	@echo "  assembly   run sbt assembly"
	@echo "  run_kz     run kafka & zookeeper"
	@echo "  stop_kz    stop kafka & zookeeper"
	@echo "  make_topic crate default topics"

compile:
	sbt compile

run:
	sbt run

test:
	sbt test

assembly:
	sbt assembly

run_kz:
	brew services start zookeeper
	brew services start kafka

stop_kz:
	brew services stop zookeeper
	brew services stop kafka

make_topic:
	kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic text_lines
	kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topic_ProducerConsumerPartitioner
	kafka-topics --create -zookeeper localhost:2181 --replication-factor 1  --partitions 1 --topic topic_AsyncProducerConsumer		
