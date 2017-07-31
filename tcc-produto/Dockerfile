FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/tcc-produto-0.0.1-SNAPSHOT.jar tcc-produto-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENV HOST_BD=""
ENV PORT_BD=""
ENV USERNAME_BD=""
ENV PASSWD_BD=""
ENV DATABASE_BD=""
ENV AUTH_DATABASE_BD=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tcc-produto-0.0.1-SNAPSHOT.jar" ]