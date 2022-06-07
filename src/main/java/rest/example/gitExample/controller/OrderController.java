package rest.example.gitExample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;
import rest.example.gitExample.service.OrderServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @GetMapping(value = "orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderServiceImpl.getOrders();
    }

    @GetMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderById(@RequestParam(value = "id") Integer id) throws OrderNotFoundException {
        return orderServiceImpl.getOrderById(id);
    }

    @PostMapping(value = "addOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO saveClient (@Valid @RequestBody OrderDTO orderDTO) {
        return orderServiceImpl.saveOrder(orderDTO);
    }
}
