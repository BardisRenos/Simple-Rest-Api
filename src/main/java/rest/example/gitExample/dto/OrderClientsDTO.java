package rest.example.gitExample.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderClientsDTO {

    private Integer id;
    private Integer orderId;
    private String productName;
    private String orderType;
    private Integer quantity;
    private Integer price;
    private String category;
    private List<ClientDTO> clients;
}
