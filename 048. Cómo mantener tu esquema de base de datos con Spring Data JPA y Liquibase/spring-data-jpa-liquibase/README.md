
# Spring Data JPA: Liquibase

Control de versiones sobre la base de datos con Liquibase.

mvn liquibase:rollback -Dliquibase.rollbackTag=version_0.2.0
mvn liquibase:rollback -Dliquibase.rollbackTag=version_0.1.0