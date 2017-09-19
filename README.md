# Web para la gestión y seleccion de personal

**Descripción de funcionalidad**

Esta web esta orientada a la selección de recursos humanos. En el ejemplo planteado los datos del dataset corresponden a personal informático: analistas, programadores, jefes de proyecto, etc. En una primera instancia el usuario selecciona el tipo de profesional que busca entre las opciones que se le plantean, a partir de ahi se empieza a mostrar un grafo que contendrá información acerca del tipo buscado. Al ir ejecutando "filtros" al seleccionar opciones entre las propuestas el grafo irá variando. 

**Ejecución**

	� Proyecto Original
	
Para ejecutarlo es necesario publicar el RDF que se encuentra en la carpeta 'dataset' en una triple store, en este caso se usar� Stardog. El repositorio que se elija es importante por el formato en el que se recibir�n los resultados. 

Por ultimo se deber� modificar los datos de conexion a la base de datos en la clase java 'Stardog.java' y ya se podr� ejecutar en un contenedor de Servlets como Tomcat. 

	� Modificaci�n del proyecto
	
La generaci�n del grafo se hace a partir de resultados de consultas en forma de tripletas, es decir, sujeto, objeto y predicado. Adem�s, para ello, se necesita un resultado en forma de Json concreto, por lo que tras la elecci�n y conecci�n a una triple store, en caso de no tratarse de Stardog, se necesita crear un "adaptador de resultados" ya que este proyecto creo un adaptador para los resultados generados por este en la clase 'Json.java'. El Json sigue el siguiente formato:

[
  {
    "source": {
      "name": "http://opendata.euskadi.eus/recurso/sector-publico/contrato/director-mendialdua"
    },
    "target": {
      "name": "2012-12-19"
    },
    "type": "http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate"
  }}]. 
  
  La clase Javascript 'GestorIndice.js' que ejecuta la llamada al servlet que realizar� la consulta a la triple store recibe unos resultados de la forma:
  http://opendata.euskadi.eus/recurso/sector-publico/contrato/director-mendialdua,2012-12-19,http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate;
  
  y es la encargada de generar un Json que siga el formato mencionado con anterioridad a partir de ellos. 
