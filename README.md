# Telegram Bot Template for LLM Integration

This project is a template for developers to create a Telegram bot that communicates with LLM (Large Language Model) API of your choice. With simple configuration, developers can connect the bot to their preferred LLM service and handle responses.

## Features

- Easy to configure: just set the bot token, API token, and API URI.
- Sends user input to the API and returns the generated response to the Telegram chat.
- The project includes logging to track user messages and bot responses in Telegram chat, as well as to log errors and monitor API responses.

## Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- A Telegram bot token from [BotFather](https://core.telegram.org/bots#botfather)
- An API token and URI from an LLM service

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ivangka/telegram-llm-bot-template.git
   cd telegram-llm-bot-template
   ```

2. Update the `Config` class with your credentials:
   ```java
   public class AppConfig {
        private static final String BOT_TOKEN = "your_telegram_bot_token";
        private static final String API_TOKEN = "your_api_token";
        private static final String API_URI = "api_uri";
        // ...
   }
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the bot:
   ```bash
   java -jar target/llm-tgbot-template.jar
   ```

### Usage

- Once the bot is running, users can send messages to the Telegram bot.
- The bot will forward the message to the selected LLM API and return the generated response to the user in Telegram.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
