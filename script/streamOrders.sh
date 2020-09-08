#!/bin/bash
############################################################################
# read  data/orders.txt and send to Kafka as stream
#
# run
# 1) launch consumer 
# kafka-console-consumer   --bootstrap-server  127.0.0.1:9092 --topic orders 
# 2) send stream
# bash script/streamOrders.sh
############################################################################

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
# please check where kafka-console-producer is in your machine, and modify below code 
done | /usr/local/opt/kafka/bin/kafka-console-producer --broker-list $BROKER --topic $TOPIC 