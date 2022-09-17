package rest.example.gitExample.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rest.example.gitExample.dao.OrderRepository;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.exception.OrderNotFoundException;
import rest.example.gitExample.resources.Order;
import rest.example.gitExample.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @BeforeEach
    void setup(){
        this.orderRepository = mock(OrderRepository.class);
        this.orderServiceImpl = new OrderServiceImpl(this.orderRepository);
    }

    @Test
    void getOrders_ShouldReturnListObjects_ValidReturn() {
        Order order1 = Order.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();
        Order order2 = Order.builder().id(2).orderId(1).productName("Eraser")
                .orderType("cash").quantity(1).price(2).category("Office").build();

        List<Order> listOfOrders = new ArrayList<>(Arrays.asList(order1, order2));

        when(orderRepository.findAll()).thenReturn(listOfOrders);

        List<OrderDTO> orderDTOs = orderServiceImpl.getOrders();
        assertAll("Return a list of OrderDTO",
                ()->assertEquals(2, orderDTOs.size()),
                ()->assertEquals("Pencil", orderDTOs.get(0).getProductName()),
                ()->assertEquals("cash", orderDTOs.get(0).getOrderType()),
                ()->assertEquals("Eraser", orderDTOs.get(1).getProductName()));
    }

    @Test
    void getOrders_ShouldReturnListObjects_NotValidReturn() {
        Order order1 = Order.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();
        Order order2 = Order.builder().id(2).orderId(1).productName("Eraser")
                .orderType("cash").quantity(1).price(2).category("Office").build();

        List<Order> listOfOrders = new ArrayList<>(Arrays.asList(order1, order2));

        when(orderRepository.findAll()).thenReturn(listOfOrders);

        List<OrderDTO> orderDTOs = orderServiceImpl.getOrders();
        assertAll("Return a list of OrderDTO",
                ()->assertNotEquals(1, orderDTOs.size()),
                ()->assertNotEquals("Stylo", orderDTOs.get(0).getProductName()),
                ()->assertNotEquals("Card", orderDTOs.get(0).getOrderType()),
                ()->assertNotEquals("Paper", orderDTOs.get(1).getProductName()));
    }

    @Test
    void getOrderById_ShouldReturnListObjects_ValidReturn() throws OrderNotFoundException {
        Order order1 = Order.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();

        when(orderRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(order1));

        OrderDTO orderDTOs = orderServiceImpl.getOrderById(1);
        assertAll("Return a list of OrderDTO",
                ()->assertEquals("Pencil", orderDTOs.getProductName()),
                ()->assertEquals("cash", orderDTOs.getOrderType()));
    }

    @Test
    void getOrderById_ShouldReturnListObjects_NotValidReturn() {
        Order order1 = Order.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();

        when(orderRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(order1));

        assertThrows(OrderNotFoundException.class, ()->orderServiceImpl.getOrderById(3));
    }
}
