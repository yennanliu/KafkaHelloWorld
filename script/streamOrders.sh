#!/bin/bash
##########################################################
# read lorders.txt and send to Kafka as stream
##########################################################

# https://github.com/spark-in-action/first-edition/blob/master/ch06/streamOrders.sh
# https://ithelp.ithome.com.tw/articles/10188761

BROKER=$1
TOPIC=orders

if [ -z "$1" ]; then
        # modify broker ip per your case
        #BROKER="192.168.56.1:9092"
        BROKER="0.0.0.0:9092"
fi

cat data/orders.txt | while read line; do
        echo "$line"
        sleep 0.1
done | /opt/kafka_2.11-0.8.2.1/bin/kafka-console-producer.sh --broker-list $BROKER --topic $TOPIC 