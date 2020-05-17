name := "KafkaHelloWorld"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  // kafka
  "org.apache.kafka" % "kafka-clients" % "2.1.1",

  // depedency 
  "org.slf4j" % "slf4j-api" % "1.7.25",

  // test
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"

)

conflictManager := ConflictManager.latestRevision
