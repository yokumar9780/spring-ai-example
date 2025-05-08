# Get Weather, Outfit, and Places by Location

This module provides functionality to fetch weather information, suggest outfits, and recommend places to visit based on a given location. It includes the following key components:

- **WeatherController**: A REST controller to handle weather-related requests.
- **WeatherTool**: A service to fetch weather data using the OpenWeather API.
- **OpenWeatherProxyClient**: A proxy client to interact with the OpenWeather API.
- **Models**: Includes `WeatherResponse`, `Location`, and other related data structures.

## Sequence Diagram

```mermaid
sequenceDiagram
    participant User
    participant WeatherController
    participant WeatherTool
    participant OpenWeatherProxyClient
    participant OpenWeatherAPI

    User->>WeatherController: GET /weather
    WeatherController->>WeatherTool: Fetch weather for location
    WeatherTool->>OpenWeatherProxyClient: Request weather data
    OpenWeatherProxyClient->>OpenWeatherAPI: API call with location
    OpenWeatherAPI-->>OpenWeatherProxyClient: Weather data response
    OpenWeatherProxyClient-->>WeatherTool: Weather data
    WeatherTool-->>WeatherController: WeatherResponse
    WeatherController-->>User: JSON response with weather, outfit, and places
```

## Implementation Details

### WeatherController

- Handles the `/weather` endpoint.
- Uses `WeatherTool` to fetch weather data and generate a response.

### WeatherTool

- Fetches weather data using `OpenWeatherProxyClient`.
- Processes the data to suggest outfits and places to visit.

### OpenWeatherProxyClient

- Interacts with the OpenWeather API to fetch weather data.
