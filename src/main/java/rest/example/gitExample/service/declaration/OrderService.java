package rest.example.gitExample.service.declaration;

import rest.example.gitExample.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders();
}
