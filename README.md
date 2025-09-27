````markdown
# Plant Manager - Backend API

Cette API REST est développée avec **Spring Boot**, **Java 17**, et expose les endpoints nécessaires pour gérer vos plantes d’intérieur, leurs besoins en arrosage (`WateringNeed`) et l’historique d’arrosage (`WateringHistory`).

---

## Fonctionnalités

- Gestion des **plantes** : création, récupération, mise à jour et suppression.
- Gestion des **besoins en arrosage** (`WateringNeed`) : création et suppression.
- Gestion de l’**historique d’arrosage** (`WateringHistory`) : création.
- Endpoints sécurisés avec **JWT**.
- Gestion des erreurs centralisée avec des exceptions personnalisées.

---

## Technologies

- **Spring Boot 3.x**
- **Spring Web**
- **Spring Security + JWT**
- **Spring Data JPA** (PostgreSQL / MySQL)
- **ModelMapper**
- **Lombok**
- **Maven / Gradle**
- **Java 17**

---

## Endpoints principaux

### Plante (`/plants`)

| Méthode | Endpoint           | Description                       |
|---------|------------------|-----------------------------------|
| POST    | /plants           | Créer une nouvelle plante         |
| GET     | /plants/{id}      | Récupérer une plante par ID       |
| GET     | /plants           | Récupérer toutes les plantes      |
| PUT     | /plants/{id}      | Mettre à jour une plante          |
| DELETE  | /plants/{id}      | Supprimer une plante              |

### Besoin d’arrosage (`/watering-needs`)

| Méthode | Endpoint                    | Description                         |
|---------|----------------------------|-------------------------------------|
| POST    | /watering-needs/create/{plantId} | Créer un besoin d’arrosage pour une plante |
| DELETE  | /watering-needs/{id}         | Supprimer un besoin d’arrosage      |

### Historique d’arrosage (`/watering-history`)

| Méthode | Endpoint                       | Description                  |
|---------|--------------------------------|------------------------------|
| POST    | /watering-history/create/{plantId} | Ajouter un arrosage pour une plante |

---

## Installation et Setup

1. **Cloner le dépôt**
````


2. **Configurer la base de données**

Modifier `application.properties` ou `application.yml` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/plant_manager
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.expiration.ms=18000000 
```

3. **Compiler et lancer l’application**

```bash
./mvnw spring-boot:run
# ou avec Gradle
./gradlew bootRun
```

L’API sera accessible sur `http://localhost:8080`.

---

## Structure du projet

```
/src/main/java/com/dev/plant_management
    /controller        # Endpoints REST
    /service           # Logique métier
    /repository        # Repositories Spring Data JPA
    /entity            # Entités JPA
    /payload/request   # DTO pour les requêtes
    /payload/response  # DTO pour les réponses
    /exceptions        # Exceptions personnalisées
```

