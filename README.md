# Web para la gestión y seleccion de personal usando LinkedData.

**Descripción de funcionalidad**

Esta web esta orientada a la selección de recursos humanos. En el ejemplo planteado los datos del dataset corresponden a personal informático: analistas, programadores, jefes de proyecto, etc. En una primera instancia el usuario selecciona el tipo de profesional que busca entre las opciones que se le plantean, a partir de ahi se empieza a mostrar un grafo que contendrá información acerca del tipo buscado. Al ir ejecutando "filtros" al seleccionar opciones entre las propuestas el grafo irá variando. 

**Ejecución**

	• Proyecto Original
	
Para ejecutarlo es necesario publicar el RDF que se encuentra en la carpeta 'dataset' en una triple store, en este caso se usará Stardog. El repositorio que se elija es importante por el formato en el que se recibirán los resultados. 

Por ultimo se deberán modificar los datos de conexion a la base de datos en la clase java 'Stardog.java' y ya se podrá desplegar en un contenedor de Servlets como Tomcat. 

	• Modificación del proyecto
	
La generación del grafo se hace a partir de resultados de consultas en forma de tripletas, es decir, sujeto, objeto y predicado. Además, para hacerlo se necesita adaptar los resultados obtenidos de la consulta al repositorio a un formato concreto, por lo que tras la elección y conección a una triple store, en caso de no tratarse de Stardog, se necesita crear un "adaptador de resultados" ya los resultados deben tener el siguiente formato: sujeto,objeto,predicado;. 
A partir de esos resultados se genera el Json final que usará la herramienta para generar el grafico que será de la forma:

[
  {
    "source": {
      "name": "sujeto"
    },
    "target": {
      "name": "predicado"
    },
    "type": "objeto"
  }}]. 
