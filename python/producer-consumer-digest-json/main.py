import threading
import logging
import time
import json
from kafka import KafkaConsumer, KafkaProducer
# UDF 
from KafkaConsumer import Consumer
from KafkaProducer import Producer

def main():
    threads = [
        Producer(),
        Consumer()
    ]
    for t in threads:
        t.start()
    time.sleep(10)

    
if __name__ == "__main__":
    logging.basicConfig(
        format='%(asctime)s.%(msecs)s:%(name)s:%(thread)d:' +
               '%(levelname)s:%(process)d:%(message)s',
        level=logging.INFO
    )
    main()