
1. Crear cuenta Microsoft Azure: https://azure.microsoft.com/free/
  * Crear suscripción
  * Crear grupo de recursos

2. Instalar el plugin Azure Toolkit en IntelliJ IDEA: https://plugins.jetbrains.com/plugin/8053-azure-toolkit-for-intellij

3. Abrir panel en IntelliJ IDEA y hacer el login en Azure

4. Crear project en Spring Apps
   * Asignar grupo recursos
   * Asignar un nombre: springproject
   * Configurar tipo de instancia

5. Crear aplicación/es dentro de springproject
   * Asignar un endpoint público

6. Desplegar aplicación/es
   * clic derecho y Desplegar

```
Start creating app(app3)...
App(app3) is successfully created.
Start updating deployment(default)...
Deployment(default) is successfully updated.
Getting deployment status...
Deployment succeeded but the app is still starting, you can check the app status from Azure Portal.
Getting public url of app(app3)...
Application url: https://app2-app3.azuremicroservices.io
Deploy succeed!
```

## Otra opción Azure CLI

https://github.com/Azure-Samples/Azure-Spring-Cloud-Samples/blob/master/java-11-sample/README.md