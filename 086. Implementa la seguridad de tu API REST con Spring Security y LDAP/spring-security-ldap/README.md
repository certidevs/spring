# Spring Security: LDAP

## Servicio de directorio

Un servicio de directorio es un tipo de almacén de datos centralizado y de control de recursos
accesibles en la red.

Recursos como:

* base de datos, bases de datos individuales
* repositorios de archivos,
* sistemas de transacciones,
* áreas de almacenamiento de archivos,
* impresoras
* y personas.

Las descripciones de las personas incluyen nombres y direcciones, roles en la organización, direcciones de correo electrónico y más.

El servicio de directorio revela elementos seleccionados de información a solicitantes legítimos y
autenticados.

Muchos proveedores han acordado el estándar LDAP para comunicarse con un servidor de directorio
y, por lo tanto, implícitamente para implementar un servidor de directorio.

## Lightweight directory access protocol (LDAP)

Es un protocolo basado en estándares que se asienta sobre TCP/IP y permite a los clientes realizar una variedad de operaciones en un servidor de directorio, incluido el almacenamiento y la recuperación de datos, la búsqueda de datos que coincidan con un conjunto determinado de criterios, la autenticación de clientes y más.


* dn: Distinguished name, a string that uniquely identifies an entry in the directory
* uid: se trata del user id o login id
* ou: Organizational Unit
* ldif: LDAP Data Interchange Format
* cn: CommonName suele hacer referencia al fullName de la persona