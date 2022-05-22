package rest.example.gitExample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dao.OrderRepository;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;
import rest.example.gitExample.mappers.OrderMapper;
import rest.example.gitExample.service.declaration.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) throws OrderNotFoundException {
        return orderRepository.findById(id).map(OrderMapper::convertEntityToDTO).orElseThrow(()-> new OrderNotFoundException(String.format("The order not found with ID : %s ", id)));
    }

}
