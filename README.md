# 🤘🏼 MetalEv-CL

**Gestor de recitales de metal en Chile** - Proyecto Java orientado a arquitectura limpia, DDD táctico y validación de negocio en el dominio.

## Descripción

MetalEv-CL es un proyecto Java cuyo objetivo es demostrar una arquitectura limpia con separación explícita entre:
- `domain`
- `application`
- `infrastructure`

Además, incorpora principios de DDD táctico:
- entidades con identidad única
- value objects auto-validantes
- agregados con comportamiento del negocio
- repositorios puros en el dominio
- casos de uso con inyección por constructor

## Requisitos Previos

- **Java 21** o superior
- **Maven 3.9** o superior
- **Git** (opcional)

### Verificar instalación

```bash
java -version
mvn -version
```

## Estructura del Proyecto

```text
metalevcl/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/mjvera/
│   │           ├── application/
│   │           │   └── usecase/
│   │           │       └── CreateRecitalUseCase.java
│   │           ├── bootstrap/
│   │           │   └── RecitalBootstrap.java
│   │           ├── domain/
│   │           │   ├── entities/
│   │           │   │   ├── Recital.java
│   │           │   │   └── Venue.java
│   │           │   ├── exception/
│   │           │   │   ├── InvalidDateRangeException.java
│   │           │   │   ├── InvalidPriceRangeException.java
│   │           │   │   └── InvalidRecitalInfoException.java
│   │           │   ├── repository/
│   │           │   │   └── RecitalRepository.java
│   │           │   └── valueobject/
│   │           │       ├── Address.java
│   │           │       ├── BandList.java
│   │           │       ├── DateRange.java
│   │           │       ├── PriceRange.java
│   │           │       ├── RecitalName.java
│   │           │       └── VenueName.java
│   │           └── infrastructure/
│   │               └── repository/
│   │                   └── InMemoryRecitalRepository.java
│   └── test/
│       └── java/
│           ├── CreateRecitalUseCaseTest.java
│           └── RecitalEntityTest.java
├── pom.xml
├── README.md
└── target/
```

## Regla de Dependencias

La dependencia sigue esta dirección:

- `infrastructure -> application/domain`
- `application -> domain`
- `domain -> ninguna capa externa`

Esto significa que:
- el dominio no conoce ni depende de infraestructura
- el caso de uso depende del repositorio del dominio
- la implementación del repositorio vive fuera del núcleo

## Ejemplo de Uso

```java
import org.mjvera.application.usecase.CreateRecitalUseCase;
import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.valueobject.Address;
import org.mjvera.infrastructure.repository.InMemoryRecitalRepository;

import java.util.List;

public class Demo {
   public static void main(String[] args) {
       var repository = new InMemoryRecitalRepository();
       var useCase = new CreateRecitalUseCase(repository);

       Venue venue = new Venue("1", "Estadio Nacional",
               new Address("Av. Grecia 2001", "Ñuñoa", "RM"));

       Recital recital = new Recital("Festival del Metal", venue,
               List.of("Ratzinger", "Chances"));

       useCase.execute(recital);
   }
}
```

## Reglas de Dominio Implementadas

- `Recital` es una entidad con identidad única (`id`)
- `Venue` es una entidad con identidad única (`id`)
- `DateRange` y `PriceRange` son `record` value objects con validación defensiva
- `RecitalName`, `BandList` y `VenueName` validan los datos del dominio
- los cambios de estado se expresan como métodos con intención de negocio, por ejemplo:
  - `renameTo(...)`
  - `moveTo(...)`
  - `addBand(...)`
  - `removeBand(...)`
  - `reprogramTo(...)`
  - `updateTicketPriceRange(...)`

## Ejecución de Pruebas

### Ejecutar todos los tests

```bash
mvn test
```

### Ejecutar una clase de test específica

```bash
mvn test -Dtest=RecitalEntityTest
```

### Ejecutar un test específico

```bash
mvn test -Dtest=CreateRecitalUseCaseTest
```

## Cobertura

El proyecto usa JaCoCo para reportar cobertura y validar calidad del código.

## Autor

**María José Vera ([majose.verap@gmail.com](mailto:majose.verap@gmail.com))**
