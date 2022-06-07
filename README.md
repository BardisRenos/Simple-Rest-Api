# Simple-RestApi's

### Info
In this repository will demostrate how to build REST API's in Java as programming language and Spring Boot as a framework. 

This repository is using:
1. Java Version 11
2. Spring Boot 2.7
3. Lombok library
4. JPA Data
5. Docker

### Adding the Resources layer
As a first step. I have to create the Resources class which are the representation of each database table. By building the entities of each table.


Below it is a small sample of the database table of **orders**. The code here creates the Class **Order** into Java code and on top of that there is the `@Table` annotation with the name of the database table on the schema part. 

```java
    @Table(name = "orders")
    public class Order implements Serializable 
```

In this part the code builds the **primary key** and the entity or the **product name** of the database table into Java environment.

```java
    @Id
    @Column(name = "order_id", updatable = false, nullable = false)
    private Integer orderId;
    @NotNull
    @Column(name = "product_name")
    private String productName;
```


### Adding the Controller layer

In order to have an entry point of the URL. It is needed to have a REST Controller class. In the case I develop and URL of `http://localhost:8081/api/v1/` then main path.

```java
    @RestController
    @RequestMapping("/api/v1/")
    @RequiredArgsConstructor
    public class OrderController
```

The following code is adding then specification of the URL path and indicates what the REST Api wants to retrieve. The full URL is : 
`http://localhost:8081/api/v1/orders`. In this case the URL indicates the retrieve all orser that are in the database.

```java
    @GetMapping(value = "orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderServiceImpl.getOrders();
    }
```

### Adding the Service layer

In this section, I add another midlle layer which is called Service or it is called Business layer. In this layer the data are been transformed into another format. Or coverign the need of the response to then end user.

```java
    @Service
    public class OrderServiceImpl implements OrderService
```

In this part. The method **getOrders()** transform the data from **Order** Entity object into **OrderDTO** object.

```java
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::convertEntityToDTO).collect(Collectors.toList());
    }
```

### Adding the DTO Mapper layer

This part is needed to tranform the Entity into DTO class.

```java
    @Service
    public class OrderMapper {

        public static OrderDTO convertEntityToDTO(Order order) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(order, OrderDTO.class);
        }
    }
```

### Adding the DTO object 

This part creates an object that will return to the end user. Therefore it is needed to be build with the specification(entities) that is needed.

```java
    @Getter
    @Setter
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    public class OrderDTO {

        private Integer orderId;
        private String productName;
        private String orderType;
        private Integer quantity;
        private Integer price;
        private String category;

    }
```

### Adding the Repository or DAO Layer

This layer is rensposible to apply the query to the database. In the below example. There is extention of the **JpaRepository** which means can be able to apply queries into the database. The most valueable info here that, it is impotant to indicates the actual type of the database table and then the primary key type. In our case, the primary key is Integer type.

```java
    @Repository
    public interface OrderRepository extends JpaRepository<Order, Integer> {


    }
```

### Lombok library 

Lombok is an additional library. The (project) Lombok is an annotation-based Java library that allows you to reduce boilerplate code. Lombok offers various annotations aimed at replacing Java code that is well known for being boilerplate, repetitive, or tedious to write. 

For example. Some annotations help to avoid to write code that is needed to return values from the class. The first annotation is used to return all values of the class. 

```java
    @Getter
```

The second annotation sets the given value to each entity. 

```java
    @Setter
 ```
 
Lastly, this annotation builds an object. The @Builder annotation lets you automatically produce the code required to have your class be instantiable with code.
 
 
 ```java
    @Builder
```

The first annotation will generate a constructor with no parameters. Where the second annotation, generates a constructor with 1 parameter for each field in your class.

```java
    @NoArgsConstructor
    @AllArgsConstructor
```


### Using Postman 

In order to test/check the RestApi it is needed to use Postman application. On the postman I added the URL in order to receive the sesponse data from the database.

```
http://localhost:8081/api/v1/clients
```
And the response from the image shows what the response has. 

<p align="center"> 
<img src="https://github.com/BardisRenos/simple-restapi/blob/main/Images/clientsResponse.png" width="350" height="450" style=centerme>
</p>

The second response comes also form the image below.

<p align="center"> 
<img src="https://github.com/BardisRenos/simple-restapi/blob/main/Images/responseOrders.png" width="350" height="450" style=centerme>
</p>

### Docker

In order to run the application. It is possible to run the application on Docker. By using Docker, can use your own environment. For example,
the first line of this Dockerfile, shows the JDK that will use. The second line, indicates in which port will be exposed.

```Docker
    FROM openjdk:11.0.15-slim
    EXPOSE 8081
    ADD target/spring-boot-application-docker.jar spring-boot-application-docker.jar
    ENTRYPOINT ["java", "-jar", "/spring-boot-application-docker.jar"]
```

FIrst of all need to build the image. With this command you can build the docker image with a specific (Docker) image name.

```Docker
    docker build -t spring_application .
```

The second command need to type, is to execute the image into docker container.  

```Docker
    docker run -p 8081:8081 spring_application
```

### Relationships between Entities

When you want to join one or more tables. Then need to indicate the table with the correct annotation. 

For example:

In this case, each Client entity may has many Orders. Therefore, adding a foreign key of client_id.

```java
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "oc_fk", referencedColumnName = "client_id")
    private List<Order> orders;
```

This image has the result of the one-to-many 

<p align="center"> 
<img src="https://github.com/BardisRenos/Simple-Rest-Api/blob/main/Images/response_Many_to_Many.png" width="350" height="450" style=centerme>
</p>


In this cas, Many Clients have many (difference) Orders. Hence, adding the pivot table in order to make the matching between the entities of Order and Client.

```java
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "client_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> ordersByClients;
```


