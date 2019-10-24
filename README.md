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



# demo-rafa
A small web service to calculate if a card is usury or not.

mvn clean package spring-boot:run to launch the project.

Once the project is compiled, you can launch the following command:

aironman@MacBook-Pro-de-Alonso ~/g/d/g/complete> java -jar target/gs-spring-boot-0.1.0.jar 

There will be a Tomcat server embedded in the jar.

Open localhost:8080 in a tab of your browser.

The COFIDIS card is currently registered with its corresponding TAE. 
See the src/main/resources/tarjetas.txt file if you want to register a new one.

The TAE data according to the BdE are registered in the file src/main/resources/types.txt, if you want to add a new one, respect the format.

Then don't forget to recompile to pack the changes in the jar.
