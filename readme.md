# Read Me First

That is demo project for spring boot + mongodb + elastic search 
deploy by docker compose.

# Requirements
* Java version 11+
* Docker Compose

# Getting Started
```bash
./mvnw clean package -DskipTests=true

docker-compose up --build
```

- can fail first time, but app server will auto restart till elastic search server ready
- visit http://localhost:8080/swagger-ui.html

## Default User

- andy@example.com
- john@example.com
- common@example.com
- lisa@example.com
- kate@example.com


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data Elasticsearch (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-elasticsearch)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

