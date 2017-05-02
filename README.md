mvn clean package

java -Dtopic={topic name} -DconfigFile={file path to config.properties} -jar target/simple-kafka-consumer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
