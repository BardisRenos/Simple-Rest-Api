# simple-restapi

### Info
In this repository will demostrate how to build REST API's in Java as programming language and Spring Boot as a framework. 

This repository is using:
1. Java Vesrtion 11
2. Spring Boot 2.7
3. Lombok library
4. JpaData
5. Docker

### Adding the Resources layer
As a first step. I have to create the Resources class which are the representation of each database table. By building the entities of each table.


Below it is a small sample of the database table of **orders**. The code here creates the Class **Order** into Java code and on top of that there is the `@Table` annotation with the name of the database table on the schema part. 

```
    @Table(name = "orders")
    public class Order implements Serializable 
```

In this part the code builds the **primary key** and the entity or the **product name** of the database table into Java environment.

```
    @Id
    @Column(name = "order_id", updatable = false, nullable = false)
    private Integer orderId;
    @NotNull
    @Column(name = "product_name")
    private String productName;
```


### Adding the Controller layer

In order to have an entry point of the URL. It is needed to have a REST Controller class. In the case I develop and URL of `http://localhost:8081/api/v1/` then main path  

```
    @RestController
    @RequestMapping("/api/v1/")
    @RequiredArgsConstructor
    public class OrderController
```

The following code is adding then specification of the URL path and indicates what the REST Api wants to retrieve. The full URL is : 
`http://localhost:8081/api/v1/orders`. In this case the URL indicates the retrieve all orser that are in the database.

```
    @GetMapping(value = "orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderServiceImpl.getOrders();
    }
```

### Adding the Service layer

In this section, I add another midlle layer which is called Service or it is called Business layer. In this layer the data are been transformed into another format. Or coverign the need of the response to then end user.

```
    @Service
    public class OrderServiceImpl implements OrderService
```

In this part. The method **getOrders()** transform the data from **Order** Entity object into **OrderDTO** object.

```
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::convertEntityToDTO).collect(Collectors.toList());
    }
```

### Adding the DTO Mapper layer

This part is needed to tranform the Entity into DTO class.

```
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

```
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


