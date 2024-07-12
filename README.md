# Project

This project is for Avows Technical Test

## Prerequisites

- Windows 10 or later
- WSL 2 installed
- A WSL 2 Linux distribution installed (e.g., Ubuntu)
- Docker Engine installed and running on WSL

## Usage

### Running Docker Compose

1. Navigate to root project directory containing the `docker-compose.yml` file:

    ```bash
    cd /path/to/your/project
    ```

2. Start the services defined in your `docker-compose.yml` file:

    ```bash
    docker compose up
    ```

3. To run Docker Compose in detached mode (in the background):

    ```bash
    docker compose up -d
    ```

4. To stop the running services:

    ```bash
    docker compose down
    ```

### Running The Program

1. Navigate to root project directory containing the `docker-compose.yml` file:

    ```bash
    cd /path/to/your/project
    ```

2. Run bash script defined in the `run.sh` file:

    ```bash
    sh run.sh
    ```

### Swagger API Documentation

This application is running on port 8080.

To access the API documentation, hit http://localhost:8080/swagger-ui/index.html

### PostgreSQL pgAdmin UI

After running docker compose, pgAdmin UI could be accessed from browser.

To access the UI, hit http://localhost:1010/browser/