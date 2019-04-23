# Base Auth2 Server

[![Build Status](https://travis-ci.org/caelcs/base-auth2-server.svg?branch=master)](https://travis-ci.org/caelcs/base-auth2-server)
[![Coverage Status](https://coveralls.io/repos/github/caelcs/base-auth2-server/badge.svg?branch=master)](https://coveralls.io/github/caelcs/base-auth2-server?branch=master)

## Overview

This projects provides you a basic auth2 server configuration powered by Spring Boot with the ability to plug custom 
login page and using Mongo DB as datastore.

## Requierments

- Java 1.8

## Setup

1. Add the dependency to your project, remember that it will work from Spring Boot 2.x onwards.
2. add the yml or property file in order to configure a few things that you might
want to customise.
3. Add the @EnableBaseAuthServer annotation to your Spring Boot app.

````java
@SpringBootApplication
@EnableBaseAuthServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
````
4. Customise the pages.

Here you have the pages that you can customise:

- Log In
- Authorise

and the following Urls:

- Log in
- Log out
- Failure url

For each you will have to create the html, css and js files following the convention below:

```$xslt
resources
    static
        css
            app.css       
    templates
        authorize.html
        login.html
    application.yml    

```

Here you have an example of how your application properties will look like:

```yaml
mongo:
  host: localhost
  port: 27017
  database: test
  username: dev
  password: dev

auth:
  logInUrl: /login
  logInViewName: login
  failureUrl: /login
  ignoreWebStaticResources:
    - /fonts/**

info:
  app:
    name: oauth2
    description: OAuth2 Provider

server:
  port: 9080
  servlet:
    context-path: /sso
```

## DB User
Create in your mongo instance the user that you will use to access the database defined in the YML file.

db.createUser(
  {
    user: "dev",
    pwd: "dev",
    roles: [ { role: "readWrite", db: "test" } ]
  }
)
