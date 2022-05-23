package rest.example.gitExample.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;
    private Integer orderId;
    private String productName;
    private String orderType;
    private Integer quantity;
    private Integer price;
    private String category;

}
