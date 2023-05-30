FROM maven:3.8.3-openjdk-17

LABEL authors="CIANDT\ericavds"
WORKDIR /app
COPY . .
ADD src /app/src
ADD pom.xml /app
RUN mvn clean install --fail-never
RUN ls
EXPOSE 8010
ENTRYPOINT ["java","-jar","target/hotelBack-0.0.1-SNAPSHOT.jar"]