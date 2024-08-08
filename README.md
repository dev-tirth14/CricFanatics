:cricket_game:Cric Fanatics:cricket_game:

Overview

Cric Fanatics is a Spring Boot-based Cricket Fantasy League application designed to help users engage in fantasy cricket tournaments :trophy:. The backend of the application is built using Spring Boot, Hibernate JPA, and MySQL, with a focus on robust service and repository layers. The application supports complex relationships between entities and features an automated data scraping mechanism to keep the database updated with the latest cricket match information.

Features 🌟
- Fantasy League Creation: Users can create and join fantasy cricket leagues.
- Team Management: Create, update, and manage cricket teams with players.
- Match Scheduling: Schedule and manage cricket matches within the leagues.
- Data Scraping: Automated scripts to scrape and update match and player data from the official T20 World Cup website.
- Player Performance Calculation: Calculates player scores based on performance stats.
- Authentication and Authorization: Secure access using Spring Security.

Built Using 🛠️
- Java 17
- Spring Boot
- Hibernate JPA
- MySQL
- Docker (optional, for containerization)
- Python 3.8+ (for scraping scripts)
- Lombok (for reducing boilerplate code)
- Spring Security (for authentication and authorization)
- Maven (for project management and build automation)


Future Enhancements 📖
- Implement a user-friendly front-end using React or Angular.
- Add real-time match updates using WebSockets.
- Expand to support multiple types of cricket tournaments and leagues.
