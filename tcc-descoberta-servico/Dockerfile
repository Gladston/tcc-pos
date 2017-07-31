FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/tcc-descoberta-servico-0.0.1-SNAPSHOT.jar tcc-descoberta-servico-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tcc-descoberta-servico-0.0.1-SNAPSHOT.jar" ]