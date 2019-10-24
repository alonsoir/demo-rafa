# demo-rafa
Un pequeño servicio web para calcular si una tarjeta es usura o no.

mvn clean package spring-boot:run para lanzar el proyecto.

Una vez compilado el proyecto, puedes lanzar el siguiente comando:

aironman@MacBook-Pro-de-Alonso ~/g/d/g/complete> java -jar target/gs-spring-boot-0.1.0.jar 

Se levantará un servidor Tomcat embebido en el jar.

Abre localhost:8080 en una pestaña de tu navegador.

Actualmente está dada de alta la tarjeta COFIDIS con su TAE correspondiente. 
Ver el fichero src/main/resources/tarjetas.txt por si quieres de alta alguna nueva.

Los datos TAE según el BdE están dado de alta en el fichero src/main/resources/tipos.txt, si quieres añadir alguna nueva, respeta el formato.

Luego no te olvides de recompilar para empaquetar los cambios en el jar.



