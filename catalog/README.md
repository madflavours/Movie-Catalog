# 🎬 Movie Repository REST API

A simple Spring Boot-based REST API for managing a collection of movies. This API supports basic CRUD operations and allows querying movies by actor name.

---

## 🚀 Features

- Create a new movie
- Retrieve all movies
- Retrieve a movie by ID
- Update an existing movie
---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven or Gradle (choose based on your setup)
- Spring Cache
- Resilience4j
- Apache Kafka

---

## 📦 API Endpoints

## 📦 API Endpoints

- `GET /movies` – Get all movies
- `GET /movies/{id}` – Get a movie by ID
- `POST /movies` – Create a new movie
- `PUT /movies/{id}` – Update an existing movie

## 📄 Sample Movie JSON

```json
{
  "title": "Avatar",
  "writer": "James Cameron",
  "year": "2009",
  "actors": [
    "Sam Worthington",
    "Zoe Saldana",
    "Sigourney Weaver",
    "Stephen Lang"
  ],
  "synopsis": "Set in the mid-22nd century, the film follows Jake Sully, a paraplegic former Marine, who is recruited to participate in the Avatar Program on the distant moon of Pandora. He becomes embroiled in a conflict between the indigenous Na'vi people and the human colonists seeking to exploit Pandora's resources.",
  "language": "English",
  "music": "James Horner",
  "producer": "James Cameron, Jon Landau",
  "director": "James Cameron",
  "budget": "$237,000,000",
  "duration": 162,
  "imdb": 7.8,
  "earnings": "$2,847,246,203",
  "rated": "Rated PG-13",
  "rottenTomatoes": "82%",
  "academyAwards": {
    "nominations": 9,
    "wins": 3
  }
}
