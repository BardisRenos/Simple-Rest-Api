package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.resources.Order;

@Service
public class OrderMapper {

    public static OrderDTO convertEntityToDTO(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }
}
