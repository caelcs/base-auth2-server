# Base Auth2 Server

[![Build Status](https://travis-ci.org/caelwinner/base-auth2-server.svg?branch=master)](https://travis-ci.org/caelwinner/base-auth2-server)

## Overview

Provides an OAuth2 server powered by Spring Boot with the ability to plug custom login page and using Mongo DB as datastore.

## Requierments
- Docker 1.7
- Java 1.8


## Build and Run docker image

In order to build and image use:
- ./gradlew buildDocker

if you want to push it
- docker push adolfoecs/base-auth2-server:0.1.0-SNAPSHOT

In order to run the image use:

- docker run -p 8580 --name base-auth2-server-instance1 -t adolfoecs/base-auth2-server:0.1.0-SNAPSHOT

## Running

In order to run the app just execute:

./gradlew bootrun

To build a jar and run all the tests (Unit and e2e):

./gradlew test assemble

the application run on http://localhost:8080.
