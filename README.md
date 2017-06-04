# OAuth 2.0 Demo Using SpringBoot.

This application allow you to play with your favourite OAuth2.0 provider API and learn how each flow works.

### Prerequisites

You need to have following in your development environment.

Java 1.8+ installed.
Maven 3+ installed.
Intellij IDEA or Eclipse installed.

## Getting Started

Start by cloning the project as follows.

git clone https://github.com/KalpaD/oauth_demo.git

1. Go to the project root directory and run the application using following command.

    ```
    mvn spring-boot: run
    ```

2. You will have the application running on

    ```
    http://localhost:8080
    ```

3. The endpoints list are as follows.

    Authorisation Code Grant Flow
    ```
    http://localhost:8080/flows?flow_type=auth_code
    ```
    Implicit grant Flow
    ```
    http://localhost:8080/flows?flow_type=implicit
    ```
    Resource Owner Credentials Grant Flow
    ```
    http://localhost:8080/flows?flow_type=ropc
    ```
    Client credentials Grant Flow
    ```
    http://localhost:8080/flows?flow_type=cc
    ```

    Check Yout Configurations
    ```
    http://localhost:8080/configs
    ```


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Kalpa Senanayake**
