# Vehicle Fake Data Generator

This module provides functionality to generate fake vehicle data using a chatbot interface. It includes the following key components:

- **VehicleChatbotController**: A REST controller to handle requests for generating vehicle data.
- **Vehicle**: A record representing the structure of a vehicle object.

## Sequence Diagram

```mermaid
sequenceDiagram
    participant User
    participant VehicleChatbotController
    participant ChatClient
    participant LLM

    User->>VehicleChatbotController: GET /vehicles?count=10
    VehicleChatbotController->>ChatClient: Generate JSON array for 10 trucks
    ChatClient->>LLM: Prompt with JSON generation request
    LLM-->>ChatClient: JSON array response
    ChatClient-->>VehicleChatbotController: List<Vehicle>
    VehicleChatbotController-->>User: JSON array of vehicles
```

## Implementation Details

### VehicleChatbotController

- Handles the `/vehicles` endpoint.
- Uses `ChatClient` to interact with the LLM for generating vehicle data.

### Vehicle

- Represents the structure of a vehicle object with fields like `id`, `vin`, `brand`, etc.
