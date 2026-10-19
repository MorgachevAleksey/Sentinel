# Sentinel
### Проект торгового робота Sentinel на Spring Boot

---
## Архитектура

- Слоистая архитектура
- Паттерн "Стратегия" (планируется для торговых стратегий)

Основной поток:
```text
Controller -> Service ─┬-> Repository → PostgreSQL
                       │
                       └-> MarketDataProvider
                                   └-> MoexMarketDataProvider
                                                 ├-> MoexClient -> MOEX ISS
                                                 └-> MoexCandlesMapper
```

---
## Road map

Реализовано:
- Инициализация проекта, слои controller/service
- Health и домашний эндпоинт
- Настройка конфигураций проекта 12 factor app: dev/prod
- Валидация конфигурации через @ConfigurationProperties
- PostgreSQL 17 в Docker, миграции Flyway
- Сущность Candle, репозиторий, сервис чтения с пагинацией
- HTTP-клиент MOEX ISS на RestClient
- DTO ответа MOEX и маппер MoexCandlesResponse -> List<Candle>
- Провайдер рыночных данных: интерфейс + реализация для MOEX

Планируется:
- Сквозной пайплайн MOEX → PostgreSQL
- Торговая стратегия Mean Reversion
- Документация Swagger
- Адаптер T-Invest Sandbox
- Перевод Gradle: Groovy -> Kotlin

---

# Эндпоинты

- GET / - приветствие и список эндпоинтов

- POST /market/{ticker}/sync - загрузка дневных свечей с MOEX в БД

- GET /info/health - статус приложения
- GET /info/status - JSON со статусом и временем

---
## Технологический стэк

- Java 21
- Spring Boot 4.1.1
- Gradle (Groovy DSL)
- SpringWeb
- RestClient
- Spring Data JPA / Hibernate
- PostgreSQL 17
- Flyway
- Docker Compose
- Spring Boot Actuator
- Bean Validation

---

## Конфигурации:

- dev - активна по умолчанию
- prod - заготовка для прода

---
## Запуск проекта
Запуск контейнера БД Docker:
- `docker compose up -d` - установка\запуск контейнера
- `docker compose ps` - получение сводки по работе

Непосредственный запуск приложения:
- `./gradlew bootRun`

Смена конфигурации:
- `SPRING_PROFILES_ACTIVE=prod ./gradlew bootRun`

---
## Дополнительно

- DECISIONS.md - файл, в котором зафиксированы этапы разрботки, описаны возникшие проблемы, обоснованы их решения\компромиссы
- Theory.md - файл, в котором зафиксирована и структурирована теория по Spring Boot, на которую обращалось особое внимание при разработке

---
*Здесь будут примеры работы приложения*