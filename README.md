# Student Management System

Une application Spring Boot complète pour la gestion des étudiants avec API REST, base de données MySQL, et documentation Swagger.

## Fonctionnalités

- ✅ Gestion CRUD des étudiants (Create, Read, Update, Delete)
- ✅ API REST avec endpoints documentés
- ✅ Base de données MySQL avec JPA/Hibernate
- ✅ Documentation automatique avec Swagger UI
- ✅ Tests unitaires avec JUnit 5 et Mockito

## Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- MySQL 8.0+

## Installation

1. Cloner le projet
```bash
git clone https://github.com/imadev26/student-management.git
cd student-management
```

2. Créer la base de données MySQL
```sql
CREATE DATABASE studentdb;
```

3. Configurer les credentials dans `src/main/resources/application.properties`

4. Exécuter l'application
```bash
mvn spring-boot:run
```

## API Endpoints

| Méthode | URL | Description |
|---------|-----|-------------|
| POST | `/students/save` | Créer un étudiant |
| GET | `/students/all` | Récupérer tous les étudiants |
| GET | `/students/count` | Compter les étudiants |
| GET | `/students/byYear` | Statistiques par année |
| DELETE | `/students/delete/{id}` | Supprimer un étudiant |

## Documentation

- **Swagger UI :** `http://localhost:8080/swagger-ui.html`
- **API Docs :** `http://localhost:8080/v3/api-docs`

## Tests

```bash
mvn test
```

## Technologies

- Spring Boot 3.5.7
- Spring Data JPA
- MySQL
- Swagger/OpenAPI
- JUnit 5 & Mockito
- Maven
