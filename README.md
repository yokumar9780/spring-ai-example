# spring-ai-workshop

The goal of this project is to provide example implementations for various AI use cases using the Spring AI framework.
This includes demonstrating how to integrate machine learning models, NLP services, and other AI capabilities into
Spring-based applications.

## Prerequisites

Before run this project, we need to have the following tools installed on machine:

- **Java 21** or higher (Make sure your JAVA_HOME environment variable is set to the correct Java version)
- **Maven 3.6+** (for building and managing the project dependencies)
- **Docker and Docker Compose** (for containerized deployment and managing services)

## Getting Started

This project demonstrates AI use cases using a Local Language Model (LLM). we can choose the model from any of the
following providers (OpenAI, Ollama, etc.), and it can be installed locally, run in Docker containers, or deployed on
cloud platforms.

Currently, this application is set to run on the Ollama model. we can follow the instructions below to install and
configure it. Later, we will extend this project to support additional model profiles, enabling use with other LLM
providers.

For Ollama:

1. Visit [Ollama’s website](https://ollama.com/) to install the Ollama platform on your machine.
2. Check available models at [Ollama’s model directory](https://ollama.com/search) and pick the models you want to use.

### Clone the Repository

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/yokumar9780/spring-ai-workshop
cd spring-ai-workshop
```

### Build the Project

Once you’ve cloned the repository, build the project using Maven to ensure all dependencies are downloaded and
everything is set up correctly:

```bash
mvn clean install
```

### Running the Application

To run the Spring application, execute the following command:

```bash
mvn spring-boot:run
```

This will start the Spring Boot application, and it will connect to the LLM model specified in the configuration. Make
sure that the LLM service (Ollama or any other) is up and running before starting the Spring application.

### Access the Application

Once the application is running, you can access it via REST APIs endpoints exposed by Spring Boot.
The application exposes REST APIs, you can test the endpoints via tools like Postman or cURL

## Configuration

The application's configuration files are located in src/main/resources/application.yaml. You can modify the
settings in this file to adjust which LLM model is being used, set API keys for cloud services, or change other
properties.

Example configuration for Ollama (to be added under application.properties):

```properties
llm.provider=ollama
ollama.api.url=http://localhost:5000
ollama.model.name=llama2
```

You can add additional configuration blocks for other LLM providers as needed (e.g., OpenAI etc...).

## Supported AI Use Cases

This project currently demonstrates several AI use cases using the Spring AI framework, including but not limited to:

1. Natural Language Processing (NLP): Text generation, classification, summarization.
2. Machine Learning Integration: Integrating machine learning models for classification or prediction tasks.
3. [AI-based Chatbots](chatbot): Using LLMs to build conversational agents.
4. Predictive Analytics: Data-driven insights and predictions using AI models.
5. Real-time AI: Real-time processing using AI models for instant predictions and feedback.