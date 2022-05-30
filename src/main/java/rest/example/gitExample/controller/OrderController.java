package rest.example.gitExample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.example.gitExample.dto.OrderClientsDTO;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;
import rest.example.gitExample.service.OrderServiceImpl;

import java.util.List;
import java.util.Locale;

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

    @GetMapping(value = "ordersByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderClientsDTO> getOrdersWithClients(@RequestParam(value = "category") String category) {
        return orderServiceImpl.getOrdersAndClients(category.toLowerCase(Locale.ROOT));
    }
}
