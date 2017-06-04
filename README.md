# OAuth 2.0 Demo Using SpringBoot.

This application allow you to play with your favourite OAuth2.0 provider API and learn how each flow works.

### Prerequisites

You need to have following in your development environment.

Java 1.8+ installed.
Maven 3+ installed.
Intellij IDEA or Eclipse installed.

## Getting Started

Start by cloning the project as follows.

    ```
    git clone https://github.com/KalpaD/oauth_demo.git
    ```

    Create GitHub application by visiting following url.

    ```
    https://github.com/settings/applications/new
    ```

    Copy the Client ID and Client Secret to relevant fields in /src/main/resources/application.yml


    Create Instagram application by visiting following url

    ```
    https://www.instagram.com/developer/ -> Manage Clients
    ```

    Copy the Client ID and Client Secret to relevant fields in /src/main/resources/application.yml


    Go to Security tab there and add following as the redirect url.

    ```
    http://localhost:8080/code
    ```

    Deselect the Disable implicit OAuth check box.

    Sample of the application.yml file

    ```
    oauth:
      clientIdGitHub: YOUR_clientIdGitHub
      clientIdIg: YOUR_clientIdIg
      clientSecretGitHub: YOUR_clientSecretGitHub
      clientSecretIg: YOUR_clientSecretIg
      redirectUrlGitHub: http://localhost:8080/redirect
      redirectUrlIg: http://localhost:8080/code
      gitHubUsername: YOUR_gitHubUsername
      gitHubPassword: YOUR_gitHubPassword
      authUrlGitHub: https://github.com/login/oauth/authorize
      authUrlIg: https://api.instagram.com/oauth/authorize
      tokenUrlGitHub: https://github.com/login/oauth/access_token
    ```

1. Go to the project root directory and run the application using following command.

    ```
    mvn spring-boot:run
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
