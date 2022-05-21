package rest.example.gitExample.resources;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(name = "order_id", updatable = false, nullable = false)
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
}
