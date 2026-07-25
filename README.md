# 🤘🏼 MetalEv-CL

**Gestor de Eventos de Metal en Chile** - Proyecto de referencia para desarrollo dirigido por pruebas (TDD)

## Descripción

MetalEv-CL es un proyecto Java enfocado en **Test-Driven Development (TDD)** que demuestra las mejores prácticas para el desarrollo de aplicaciones mantenibles y confiables. El proyecto gestiona eventos de metal realizados en Chile con validaciones robustas y una cobertura de código del 100%.

Este es un proyecto educativo creado como parte del Curso **Fundamentos de JAVA- Globant Talento Ready** que sirve como referencia para implementar TDD en Java usando JUnit 5 y Mockito.

## Requisitos Previos

- **Java 21** o superior
- **Maven 3.9** o superior
- **Git** (opcional, para clonar el repositorio)

### Verificar instalación

```bash
java -version
mvn -version
```

## Instalación y Configuración

### 1. Clonar o descargar el proyecto

```bash
git clone <url-del-repositorio>
cd metalevcl
```

### 2. Instalar dependencias

```bash
mvn clean install
```

Este comando:
- Limpia compilaciones previas
- Descarga todas las dependencias
- Ejecuta los tests
- Genera reportes de cobertura

## Ejecución de Pruebas

### Ejecutar todos los tests

```bash
mvn test
```

### Ejecutar una clase de test específica

```bash
mvn test -Dtest=EventServiceTest
```

### Ejecutar un test específico

```bash
mvn test -Dtest=EventServiceTest#shouldThrowInvalidEventExceptionWhenEventDataIsInvalid
```

### Compilar sin ejecutar tests

```bash
mvn clean compile
```

## Cobertura de Código

El proyecto utiliza **JaCoCo** para garantizar una cobertura de código del **100%** en líneas y ramas.

### Generar reporte de cobertura

```bash
mvn clean test
```

El reporte se genera en: `target/site/jacoco/index.html`


## Estructura del Proyecto

```
metalevcl/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/mjvera/
│   │           ├── EventService.java           # Servicio principal de eventos
│   │           ├── EventRepository.java        # Interfaz para persistencia
│   │           ├── models/
│   │           │   └── EventModel.java         # Modelo de datos de eventos
│   │           └── exceptions/
│   │               ├── InvalidEventInfoException.java
│   │               ├── InvalidDateRangeException.java
│   │               └── InvalidPriceRangeException.java
│   └── test/
│       └── java/
│           ├── EventServiceTest.java           # Tests del servicio
│           └── EventModelTest.java             # Tests del modelo
├── pom.xml                                     # Configuración de Maven
└── README.md                                   # Este archivo
```

## Conceptos TDD Implementados

### 1. **Test-First Approach**
   - Los tests se escriben antes de la implementación
   - La implementación se desarrolla para pasar los tests

### 2. **Inyección de Dependencias**
   - `EventService` recibe `EventRepository` como dependencia
   - Facilita testing con mocks

### 3. **Mocking**
   ```java
   @Mock
   private EventRepository eventRepository;
   
   @InjectMocks
   private EventService eventService;
   ```

### 4. **Tests Parametrizados**
   - Validación de múltiples casos con una sola prueba
   - Uso de `@ParameterizedTest` y `@MethodSource`

### 5. **Cobertura del 100%**
   - Líneas y ramas cubiertas
   - Garantiza calidad y confiabilidad del código

## Ejemplo de Uso

### Crear un evento

```java
// Crear modelo de evento
EventModel event = new EventModel(
    "South American Tour 2026",
    "Teatro Caupolicán",
    List.of("Children Of Boom", "Korpiklaani")
);

// Usar servicio (con repositorio real o mock)
EventService service = new EventService(repository);
service.createEvent(event);
```

### Manejo de excepciones

El proyecto valida:
- Nombre del evento no vacío
- Lugar del evento no vacío
- Lista de bandas no vacía
- Rango de fechas válido
- Rango de precios válido

```java
// Esto lanzará InvalidEventInfoException
EventModel invalidEvent = new EventModel("", "Venue", List.of("Band"));
service.createEvent(invalidEvent); // Excepción
```

## TODO - Mejoras Futuras

Las siguientes características están planeadas para futuras versiones:

### Refactorización de Entidades
- [ ] **Recinto como entidad propia** - Crear clase `VenueModel` independiente
  - Separar la lógica del recinto de `EventModel`
  - Permitir reutilizar recintos entre eventos
  - Agregar validaciones específicas de recinto (capacidad, ubicación, etc.)

- [ ] **Agregar Productora** - Crear clase `PromoterModel`
  - Incorporar información de la productora del evento
  - Relacionar productora con uno o múltiples eventos

### Validaciones y Requisitos
- [ ] **Cambiar requisitos mínimos de eventos**
  - Revisar qué campos son realmente obligatorios
  - Flexibilizar la lista de bandas (opcional vs obligatorio)

- [ ] **Hacer obligatorias fechas de inicio y final**
  - Agregar campos `startDate` y `endDate` obligatorios a `EventModel`
  - Tests parametrizados para casos de fechas inválidas
  - Garantizar cobertura del 100% en nuevas validaciones

## Autor

**María José Vera ([majose.verap@gmail.com](mailto:majose.verap@gmail.com))**
