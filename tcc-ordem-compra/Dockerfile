FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/tcc-ordem-compra-0.0.1-SNAPSHOT.jar tcc-ordem-compra-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tcc-ordem-compra-0.0.1-SNAPSHOT.jar" ]