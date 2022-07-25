## workshop 13

* Run the springboot app


> mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=C:\Users\Brendon\JavaProjects\ssfworkshop3


* Incorporate test dependencies

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>2.7.1</version>
    <scope>test</scope>
<dependency>
```

* In order to execute test cases

> mvn test
