FROM adoptopenjdk/openjdk8
RUN mkdir /opt/shareclasses
RUN mkdir /opt/app
ARG JAR_FILE
ADD src/test/resources/tipos.txt /opt/app/tipos.txt
ADD src/test/resources/tarjetas.txt /opt/app/tarjetas.txt
ADD target/${JAR_FILE} /opt/app/app.jar
RUN apt-get update
RUN apt-get install -y net-tools
CMD ["java", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-Xscmx128m", "-Xscmaxaot100m", "-Xshareclasses:cacheDir=/opt/shareclasses", "-jar", "/opt/app/app.jar"]