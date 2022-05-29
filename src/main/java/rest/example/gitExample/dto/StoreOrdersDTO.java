package rest.example.gitExample.dto;

import lombok.*;
import rest.example.gitExample.resources.Order;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class StoreOrdersDTO {

    private Integer id;
    private Integer storeId;
    private String storeName;
    private String storeCity;
    private String storeCountry;
    private List<Order> orders;
}
