### Notice
- This project can also be run via
	- basic kafka
	- kafka stream

### Ref
- https://kafka-tutorials.confluent.io/create-stateful-aggregation-count/kstreams.html

### Quick Start
```bash
#--------------------------------------
#  PART 1 : RUN DOCKER
#--------------------------------------
git clone https://github.com/yennanliu/KafkaHelloWorld.git
cd KafkaHelloWorld/examples/count_a_stream_of_events
docker-compose up -d

#--------------------------------------
#  PART 2 : INTO KAFKA ENV
#--------------------------------------
# into kafka broker env (as root user)
docker exec -u root -it broker bash 

#--------------------------------------
#  PART 3 : INSTALL DEPEDENCY
#--------------------------------------
yum install git unzip nano which

# install gradle
# https://linuxize.com/post/how-to-install-gradle-on-centos-7/
wget https://services.gradle.org/distributions/gradle-5.0-bin.zip -P /tmp
unzip -d /opt/gradle /tmp/gradle-5.0-bin.zip
ls /opt/gradle/gradle-5.0
nano /etc/profile.d/gradle.sh
# export GRADLE_HOME=/opt/gradle/gradle-5.0
# export PATH=${GRADLE_HOME}/bin:${PATH}

chmod +x /etc/profile.d/gradle.sh
source /etc/profile.d/gradle.sh
# verify if gradle is installed
# https://askubuntu.com/questions/483552/gradle-finds-wrong-java-home-even-though-its-correctly-set-ubuntu-13-10
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
gradle -v

#--------------------------------------
#  PART 4 : INIT JAVA PROJECT (gradle) & GET FILES
#--------------------------------------
gradle wrapper

git clone https://github.com/yennanliu/KafkaHelloWorld.git
cp -fr KafkaHelloWorld/examples/count_a_stream_of_events/src/ .
cp -fr KafkaHelloWorld/examples/count_a_stream_of_events/configuration/ .

# compile 
./gradlew build
```