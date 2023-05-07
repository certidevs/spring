
# Spring Native: imágenes nativas empaquetadas Docker

## 1. NATIVE

https://github.com/graalvm/graalvm-ce-builds/releases

wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.2.0/graalvm-ce-java17-linux-amd64-22.2.0.tar.gz

tar -xzf graalvm-ce-java17-linux-amd64-22.2.0.tar.gz

export PATH=/mnt/c/dev/spring/talleres-clase/spring-native/graalvm-ce-java17-22.2.0/bin:$PATH

export JAVA_HOME=/mnt/c/dev/spring/talleres-clase/spring-native/graalvm-ce-java17-22.2.0

java -version

mvn spring-boot:build-image

Crear contenedor docker: 

docker run --rm --name springnative -p 8080:8080 spring-native:0.0.1-SNAPSHOT

ver: docker image list

Se puede apreciar como la aplicación arranca en milisegundos


## 2. TRADICIONAL

Hacer mvn package para generar el archivo jar.

Dockerfile

Construir imagen docker 

Crear contenedor docker

Se puede apreciar como la aplicación tarda segundos en arrancar, dependiendo de cuánto código tenga
