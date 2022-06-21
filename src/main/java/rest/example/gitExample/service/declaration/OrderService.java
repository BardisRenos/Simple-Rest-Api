package rest.example.gitExample.service.declaration;

import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;

import java.util.List;

/**
 * Order Service Interface
 */
public interface OrderService {

    List<OrderDTO> getOrders();
    OrderDTO getOrderById(Integer id) throws OrderNotFoundException;
    OrderDTO saveOrder(OrderDTO orderDTO);
}
