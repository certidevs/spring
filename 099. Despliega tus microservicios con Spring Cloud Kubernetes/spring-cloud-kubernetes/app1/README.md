
# Spring Cloud: Kubernetes


## Instalar entorno

0. Docker
1. Kubectl: https://kubernetes.io/docs/tasks/tools/install-kubectl/
2. Minikube: https://minikube.sigs.k8s.io/docs/start/
    * https://github.com/kubernetes/minikube/releases

Upgrade (Windows): choco upgrade minikube


## Arrancar entorno

Iniciar docker

minikube version

minikube addons list 

minikube start 

minikube start --kubernetes-version=v1.24.3

minikube dashboard

## Docker container

Crear el Dockerfile

mvn clean package

docker login

docker build -t alansastre/app1:1.0 .

docker push alansastre/app1:1.0


## Deployment

Crear archivo deployment.yaml

kubectl apply -f deployment.yaml

minikube ip

kubectl get pods 

kubectl logs app1

## Service

kubectl get services

minikube service app1


## ConfigMaps

kubectl create -f deployment-config.yaml

kubectl get configmaps

kubectl delete configmap app1


## Parar y limpiar entorno

Opción 1:

kubectl delete service app1
kubectl delete deployment app1

Opción 2:

kubectl delete -f deployment.yaml

Parar y borrar contenedor minikube:

minikube stop

minikube delete



