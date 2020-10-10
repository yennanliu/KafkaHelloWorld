#!/bin/sh

echo '>>>> DOCKER BUILD...'
docker build services/scala/. -t kafka_env
echo '>>>> RUN sbt test & sbt build...'
docker run --mount \
type=bind,\
source="$(pwd)"/.,\
target=/kafka_env \
-i -t kafka_env \
/bin/bash  -c "cd ../kafka_env && sbt compile && sbt test &&  sbt assembly"
 