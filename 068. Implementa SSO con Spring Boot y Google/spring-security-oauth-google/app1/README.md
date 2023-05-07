
1. https://console.cloud.google.com/
2. Crear un proyecto si no tenemos ninguno
3. OAuth consent screen
4. Credentials
   * Habilitar urls: http://localhost:8080, http://localhost:8081
5. Añadir configuración spring en application.properties
   * spring.security.oauth2.client.registration.google.client-id=...
   * spring.security.oauth2.client.registration.google.client-secret=...
   * spring.security.oauth2.client.registration.google.scope=profile,email