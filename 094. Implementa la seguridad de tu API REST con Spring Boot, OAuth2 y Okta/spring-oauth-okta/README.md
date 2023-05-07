# Spring Security: OAuth2 con Okta

## Okta

Instalar Okta CLI: https://cli.okta.com/
Instalar httpie: https://httpie.io/cli


### Opción 1

```bash
$ okta register
```

1. Entrar en okta
2. Security
3. API
4. Tokens
5. Crear nuevo token
6. Por consola ejecutar ``okta login`` e introducir url, usuario y token
7. ``okta apps create`` Seleccionar opción de aplicación Web
8. Acceder al endpoint securizado y ver cómo nos redirige automáticamente al login de Okta


### Opción 2

Crear aplicación Single-Page APP en Okta

Otra opción: Generar token JWT: 

1. Entrar en la app de okta y añadir en login

https://oidcdebugger.com/debug

2. Vamos a https://oidcdebugger.com/

* Authorize URI: https://dev-xxxxxx.okta.com/oauth2/default/v1/authorize
* Client Id: 0oa6npgtqpbuHrcoX5d7
* Scope: openid
* Response type: code

El código que devuelve sería similar a este: 

lDboaCFhlnbziT7AGZzEKxSZ7gTQw7Hli0UTmBy1Tx0

Lo utilizamos por consola con una petición como la siguiente: 

http -f https://dev-xxxxxx.okta.com/oauth2/default/v1/token \
grant_type=authorization_code \
code=KB8ru-UEndXHnkmc7vPxDutOAfcjSPbiADBPvTDbCzQ \
client_id=0oa6npgtqpbuHrcoX5d7 \
client_secret=4Vu5BPze3E5UdS41u6vJ1z1H6qU2EGTpiHhuLcmy \
redirect_uri=https://oidcdebugger.com/debug

Devuelve el token JWT

El token JWT se puede utilizar en todas las peticiones en una cabecera Authorization: Bearer <token>


## OAuth Grant Types:

* Authorization Code
* PKCE
* Client Credentials
* Device Code
* Refresh Token

## Authorization Code

The Authorization Code flow is the most common OAuth flow. It is used by web apps and mobile apps that can securely store a Client Secret.

Explain authorization code in oauth 2.0:

* The user is redirected to the authorization server.
* The user authenticates and approves the authorization request.
* The authorization server redirects the user back to the application with an authorization code.
* The application exchanges the authorization code for an access token and a refresh token.
* The application uses the access token to access the resource.
* The application uses the refresh token to get a new access token when the current access token expires.

## PKCE

The PKCE flow is used by public clients (mobile apps, single page apps, and JavaScript apps) that cannot securely store a Client Secret.

How pkce grant type works:

1. Your application (app) generates a code verifier followed by a code challenge.
2. Your app sends the code challenge to the authorization server.
3. The user is redirected to the authorization server.
4. The user authenticates and approves the authorization request.
5. The authorization server redirects the user back to the application with an authorization code.
6. Your app sends the authorization code and code verifier to the authorization server.
7. The authorization server validates the code verifier and returns an access token and a refresh token.
8. Your app uses the access token to access the resource.
9. Your app uses the refresh token to get a new access token when the current access token expires.



## Client Credentials

The Client Credentials flow is used by server-to-server applications that do not have a user interface. It is used to authenticate a client application to an API.

How client credentials grant type works:

1. Your application (app) sends the client ID and client secret to the authorization server.
2. The authorization server validates the client ID and client secret and returns an access token.
3. Your app uses the access token to access the resource.
4. Your app uses the refresh token to get a new access token when the current access token expires.



## Device Code

The Device Code flow is used by devices that do not have a browser or a way to enter text. It is used to authenticate a device to an API.

How device code grant type works:

1. Your application (app) sends the client ID to the authorization server.
2. The authorization server returns a device code, user code, verification URI, and verification URI complete.
3. Your app prompts the user to go to the verification URI and enter the user code.
4. The user authenticates and approves the authorization request.
5. The authorization server returns an access token and a refresh token.
6. Your app uses the access token to access the resource.
7. Your app uses the refresh token to get a new access token when the current access token expires.

## Refresh Token

The Refresh Token flow is used to refresh an access token. It is used to extend the lifetime of an access token.

How refresh token grant type works:

1. Your application (app) sends the client ID, client secret, and refresh token to the authorization server.
2. The authorization server validates the client ID, client secret, and refresh token and returns a new access token.
3. Your app uses the new access token to access the resource.
4. Your app uses the refresh token to get a new access token when the current access token expires.
