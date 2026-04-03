## StreamFlix: Event-driven microservices system demonstrating real-time communication using Kafka.

## Project Structure
- StreamFlix
  - streamflix-events
    - MovieAddedEvent
    - CustomerGenreUpdateEvent
  - customer-service
  - movie-service
  - recommendation-service

## To run this project
- Ensure kafka broker is running in you system 
- Start customer-service
- Start movie-service
  - enable movie import in movie-service: app.import-movies=true
- Start recommendation-service
- Open http://localhost:8080 in browser