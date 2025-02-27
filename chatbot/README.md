# chatbot

Please refer to the [README](../README.md) file for setting up the application.
For now, use the endpoint mentioned below to run the chatbot feature

## Fake data Generator

### Endpoints for Movies

#### Get top 10 actions movies for 2024 year

```bash
curl --location 'http://localhost:8081/movies?category=action&year=2024&count=10'
```

#### Get top 10 comedy movies for 2024 year

```bash
curl --location 'http://localhost:8081/movies?category=comedy&year=2024&count=10'
```

#### Get the movie details form the spring in-memory cache

```bash
curl --location 'http://localhost:8081/movies/2'
```

### Endpoints for Persons

#### Get 10 most famous persons list

```bash
curl --location 'http://localhost:8081/persons?count=10'
```

## Free text search

#### search for topic 'why the sky is blue?'

```bash
curl --location 'http://localhost:8081/text?topic=whythe%20sky%20is%20blue%3F'
```

#### search for topic 'How many days are there in each month?'

```bash
curl --location 'http://localhost:8081/text?topic=How%20many%20days%20are%20there%20in%20each%20month%3F'
```

#### search for topic 'What's the weather like in india?'

```bash
curl --location 'http://localhost:8081/weather?message=What%27s%20the%20weather%20like%20in%20India%3F'
```

#### search for topic 'What's the weather like in india?'

```bash
curl --location 'http://localhost:8081/weather' \
--header 'Content-Type: application/json' \
--data '{
    "latitude": 57.704839266417736,
    "longitude": 11.916077867119549
}
'
```