cd %0
cd ..
cd ./production-api
start mvn install
cd ..
cd production-service
start mvn package
cd target
start java -jar production-service-0.0.1-SNAPSHOT.jar
cd ..
