FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY
COPY --chown=node:node ./package*.json ./
##COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
##COPY ${DEPENDENCY}/META-INF /app/META-INF
##COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8081
HEALTHCHECK --interval=5s --timeout=3s --start-period=30s --retries=2 CMD nc -nz localhost 8081 || exit 1
ENTRYPOINT ["java","-cp","app:app/lib/*","arcmop.blog.springbootest.configuracion.StartApplication"]
