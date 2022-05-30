package rest.example.gitExample.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "order_id")
    private Integer orderId;
    @NotNull
    @Column(name = "product_name")
    private String productName;
    @NotNull
    @Column(name = "order_type")
    private String orderType;
    @NotNull
    @Column(name = "quantity")
    private Integer quantity;
    @NotNull
    @Column(name = "price")
    private Integer price;
    @NotNull
    @Column(name = "category")
    private String category;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orders")
    private List<Client> clients;

    /**
     * The constructor
     * @param id The id of  Client database table
     * @param orderId The orderId of each order
     * @param productName  the product name of the order
     * @param orderType The order type of the order
     * @param quantity The quantity of the product on the order
     * @param price The price of the product on the order
     * @param category The category of the product on the order
     */
    public Order(Integer id, Integer orderId, String productName, String orderType, Integer quantity, Integer price, String category) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.orderType = orderType;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}
