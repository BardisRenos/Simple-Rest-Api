package rest.example.gitExample.dto;

import lombok.*;
import rest.example.gitExample.resources.Order;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientOrdersDTO {

    private String clientName;
    private String clientLastName;
    private String address;
    private String phoneNumber;
    private List<Order> orders;

}
