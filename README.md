

# SprintBoot Rest API boilerplate code

## Covered items

    - Postgres With UUID Primary key
    - Auditable entity and BaseEntity
    - ByCrypt Password encription
    - ExtendedJpaRepository
    - Exception handler
    - ResponseWrapper class
    - ResultSet for Response
    - Object merge Utils

## todo
    - dotenv
    - Swagger
    - JWT Auth
    - Docker
    - Linting
    - Sonarqube setup
    - Tesing 
    - Seeding
    
## Setup Notes
    - Use sdkman to manage depndencies [sdkman installation link](https://sdkman.io/install)

##Follow below steps for creating build

```bash
   $ gradle clean build
   $ java -jar build/libs/RESTSpringTest-0.0.1-SNAPSHOT.jar
```