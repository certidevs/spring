
# Seguridad X.509

Crear dos aplicaciones que se comuniquen utilizando TLS mutuo, cada una con su propio
certificado digital.

## Requisitos

Tener Java descargado y configurado en la ruta PATH.

## keystore server1:

keytool -genkeypair -alias server1 -keyalg RSA -keysize 2048 -dname "CN=server1" -validity 365 -storetype PKCS12 -keystore s1_ks.p12 -storepass admin123

keytool -exportcert -alias server1 -storetype PKCS12 -keystore s1_ks.p12 -file s1_cert.cer -rfc -storepass admin123

## keystore server2:

keytool -genkeypair -alias server2 -keyalg RSA -keysize 2048 -dname "CN=server2" -validity 365 -storetype PKCS12 -keystore s2_ks.p12 -storepass admin123

keytool -exportcert -alias server2 -storetype PKCS12 -keystore s2_ks.p12 -file s2_cert.cer -rfc -storepass admin123

## truststore S1 IMPORT S2:

keytool -importcert -alias server2 -storetype PKCS12 -keystore s1_ts.p12 -file s2_cert.cer -rfc -storepass admin123

## truststore S2 IMPORT S1:

keytool -importcert -alias server1 -storetype PKCS12 -keystore s2_ts.p12 -file s1_cert.cer -rfc -storepass admin123

## Cambiar DNS

Para windows:
C:\Windows\System32\drivers\etc\hosts

127.0.0.1 server1 server2

Para Linux/MacOS:

/etc/hosts

## Configuración Spring Boot

Cada app Spring tendrá su propio keystore y truststore
