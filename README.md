# Log Analyzer

The Log Analyzer is a Java application designed to parse and analyze log entries from various sources, including Kubernetes, Docker, Azure App Services, VMs, and Elasticsearch. It is equipped to identify and extract relevant information such as timestamps, log levels, thread names, logger names, messages, and stack traces.

## Features

- Fetch logs from Kubernetes, Docker, Azure App Services, VMs, and Elasticsearch.
- Parse log entries from different sources and formats.
- Identify and extract relevant information from log entries.
- Handle multi-line stack traces.
- Customize log parsing logic for different log formats.

## Prerequisites

- Java Development Kit (JDK) 8 or later installed.
- Maven build tool for compiling and packaging the application.
- Docker, if you intend to run the application in a Docker environment.

## Usage

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/sauravkumarshah/log-analyzer.git
   cd log-analyzer
   ```

2. **Build the Application:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   java -jar target/log-analyzer.jar
   ```

   Ensure that you have appropriate permissions and dependencies installed to access log sources.

## Configuration

Modify the `application.properties` file to configure the application according to your specific requirements. You can customize log file paths, logging levels, and other settings.

```properties
# Example configuration properties
log.source=kubernetes
log.file.path=/var/log/kubernetes/application.log
log.level=ERROR
```

## Docker Support

If you prefer running the application in a Docker container, you can build the Docker image using the provided Dockerfile.

1. **Build Docker Image:**

   ```bash
   docker build -t log-analyzer .
   ```

2. **Run Docker Container:**

   ```bash
   docker run log-analyzer
   ```
