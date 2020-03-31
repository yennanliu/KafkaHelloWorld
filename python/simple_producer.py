from time import sleep
from json import dumps
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda x: 
                         dumps(x).encode('utf-8'))

# send data {'number' : e} to topic "first_topic"
for e in range(50):
    data = {'number' : e}
    producer.send('first_topic', value=data)
    sleep(1)