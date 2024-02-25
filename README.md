A service provides API to get praying times.


To make local build
podman run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -w /usr/src/mymaven docker.io/library/maven:3-eclipse-temurin-21-alpine mvn clean install

$ docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -v "$PWD/target:/usr/src/mymaven/target" -w /usr/src/mymaven maven mvn clean package  