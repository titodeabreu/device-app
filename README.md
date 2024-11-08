# Device Management App

## Overview

The **Device Management App** is a spring boot application made on Java 21 that provides a RESTful API for managing devices. The application includes thw following endpoints:

1. **Create Device**: Allows users to create a new device.
2. **Delete Device by ID**: Allows users to delete a device.
3. **Get Device by ID**: Retrieves details about a specific device by device ID.
4. **Get All Devices**: Retrieves details about all devices registered in db.
5. **Patch Device**: Update partially all the fields available for a device
6. **PUT Device**: Update all the fields available for a device

## Technologies Used

- Java 21
- Spring boot 3.3.5
- H2 in memory db
- MapStruct
- Lombok
- Swagger

## Build and Run (Docker)

### 1. Running with Docker

```
docker build -t device-management-image .
```

```
docker run --name device-management-app -p 8080:8080 device-management-image
```

## Build and Run (Dev)

### 1. Build Project

```
./gradlew clean build
```

### 2. Running unit tests

```
./gradlew test
```

### 3. Running in Development Mode

```
./gradlew bootRun
```

## Swagger UI

```
http://localhost:8080/swagger-ui/index.html#/
```

## Postman Collection can be found at

```
resources/postman/Device Postman Collection.postman_collection.json
```
