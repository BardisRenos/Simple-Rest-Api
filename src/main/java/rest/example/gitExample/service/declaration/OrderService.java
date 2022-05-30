package rest.example.gitExample.service.declaration;

import rest.example.gitExample.dto.OrderClientsDTO;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders();
    OrderDTO getOrderById(Integer id) throws OrderNotFoundException;
    List<OrderClientsDTO> getOrdersAndClients(String category);
}
