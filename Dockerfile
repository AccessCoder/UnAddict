FROM openjdk:17

MAINTAINER AccessCoder <andro5@web.de>

ADD backend/target/UnAddict.jar UnAddict.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$URI -jar /UnAddict.jar" ]