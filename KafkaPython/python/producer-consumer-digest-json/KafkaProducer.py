import threading
import logging
import time
import json
from kafka import KafkaConsumer, KafkaProducer

class Producer(threading.Thread):
    daemon = True
    def run(self):
        producer = KafkaProducer(
        	bootstrap_servers=['localhost:9092'],
            value_serializer=lambda v: json.dumps(v).encode('utf-8'))
        
        while True:
            producer.send('first_topic', {"dataObjectID": "test1"})
            producer.send('first_topic', {"dataObjectID": "test2"})
            time.sleep(1)