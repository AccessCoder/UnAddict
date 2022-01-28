FROM openjdk:17

MAINTAINER AccessCoder <andro5@web.de>

ADD backend/target/unaddict.jar unaddict.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$URI -jar /unaddict.jar" ]
