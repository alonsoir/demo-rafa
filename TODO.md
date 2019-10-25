hay que coger los datos de la web https://clientebancario.bde.es/pcb/es/menu-horizontal/productosservici/relacionados/tiposinteres/guia-textual/tiposinteresprac/Tabla_de_tipos__a0b053c69a40f51.html
en concreto los valores del TAE para españa.

un controller, OK
refactorizar, OK
un html, OK
dockerizar, OK
tests, OK

usar https://github.com/AdoptOpenJDK/homebrew-openjdk OK

Con problemas, el proposito de usar esa JVM es usar un comando como el siguiente:


java -jar -Xmx128m 
          --XX:+IdleTuningGcOnIdle 
          -Xtune:virtualized 
          -Xscmx128m 
          -Xscmaxaot100m 
          -Xshareclasses:cacheDir=/opt/shareclasses 
          /opt/app/app.jar 
          /opt/app/tipos.txt 
          /opt/app/tarjetas.txt

Y actualmente, con adoptopenjdk/openjdk8, solo puedo lanzarlo con:

java -jar -Xmx128m 
          /opt/app/app.jar 
          /opt/app/tipos.txt 
          /opt/app/tarjetas.txt

Que es lo mismo que me da una JVM normal y corriente...