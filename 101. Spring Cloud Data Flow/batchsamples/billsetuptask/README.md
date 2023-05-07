
## Proyecto ejemplo:

https://github.com/spring-cloud/spring-cloud-dataflow-samples/blob/main/dataflow-website/batch-developer-guides/batch/batchsamples/dist/batchsamples.zip

## SCDF en docker 

Descargar y añadir al proyecto

https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/v2.9.1/src/docker-compose/docker-compose.yml

Variables de entorno:

Windows powershell:
$Env:DATAFLOW_VERSION="2.9.2"
$Env:SKIPPER_VERSION="2.8.2"
$Env:HOST_MOUNT_PATH="~/.m2"
$Env:DOCKER_MOUNT_PATH="/home/cnb/.m2"

docker-compose up -d

## Crear una aplicación

* Short lived batch task applications: Spring Cloud Task
* Long-running real time streaming applications: Spring Cloud Stream

Para este ejemplo usaremos una Spring Cloud Task con el ejemplo billsetuptask.

Configurar JDK 8 en el proyecto.

mvn clean install lo cual instala el archivo jar en el repositorio local de maven.

## Cargar/Registrar una aplicación en SCFD

http://localhost:9393/dashboard

Otra opción es entrar en la consola dataflow

file://home/cnb/.m2/repository/io/spring/billsetuptask/0.0.1-SNAPSHOT/billsetuptask-0.0.1-SNAPSHOT.jar

## Crear la tarea en SCFD

Ir al menú de Tasks y crear una nueva tarea indicando las aplicaciones que se van a usar.

## Ejecutar una aplicación

Desde el menú Tasks ejecutar la tarea creada en Launch Task.

Ver los logs de la tarea entrando en ejecuciones.

## Limpiar el entorno y borrar contenedores

docker-compose down

docker rm dataflow-app-import-task
docker rm dataflow-app-import-stream
docker rm dataflow-server
docker rm dataflow-mysql
docker rm dataflow-kafka-zookeeper
docker rm dataflow-kafka
docker rm skipper