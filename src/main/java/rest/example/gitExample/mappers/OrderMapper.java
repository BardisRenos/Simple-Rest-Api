package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.dto.OrderClientsDTO;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.resources.Order;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    /**
     * Convert an Order entity into OrderDTO
     * @param order The Order entity class
     * @return A OrderDTO object
     */
    public static OrderDTO convertEntityToDTO(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }

    /**
     * Convert an OrderDTO object into Order entity
     * @param orderDTO The OrderDTO
     * @return A Order entity
     */
    public static Order convertDTOtoEntity(OrderDTO orderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderDTO, Order.class);
    }

    public static OrderClientsDTO convertEntityToOrderClientDTO(Order order) {
        OrderClientsDTO orderClientsDTO =  new OrderClientsDTO();
        List<ClientDTO> clientList;

        orderClientsDTO.setId(order.getId());
        orderClientsDTO.setOrderId(order.getOrderId());
        orderClientsDTO.setProductName(order.getProductName());
        orderClientsDTO.setOrderType(order.getOrderType());
        orderClientsDTO.setQuantity(order.getQuantity());
        orderClientsDTO.setPrice(order.getPrice());
        orderClientsDTO.setCategory(order.getCategory());

       clientList = order.getClients().stream().map(clientIndex -> new ClientDTO(clientIndex.getClientName(), clientIndex.getClientLastName(),
                    clientIndex.getAddress(), clientIndex.getPhoneNumber())).collect(Collectors.toList());

        orderClientsDTO.setClients(clientList);

        return orderClientsDTO;
    }
}
