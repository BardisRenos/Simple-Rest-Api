package rest.example.gitExample.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rest.example.gitExample.controller.OrderController;
import rest.example.gitExample.dto.OrderDTO;
import rest.example.gitExample.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderServiceImpl orderServiceImpl;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.orderController = new OrderController(this.orderServiceImpl);
    }

    @Test
    void getAllOrders_ShouldReturnListOfObjects_ValidReturn() throws Exception {
        OrderDTO order1 = OrderDTO.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();
        OrderDTO order2 = OrderDTO.builder().id(2).orderId(1).productName("Eraser")
                .orderType("cash").quantity(1).price(2).category("Office").build();

        List<OrderDTO> listOfOrders = new ArrayList<>(Arrays.asList(order1, order2));

        when(orderServiceImpl.getOrders()).thenReturn(listOfOrders);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(listOfOrders)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(listOfOrders.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId", is(listOfOrders.get(0).getOrderId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", is(listOfOrders.get(0).getProductName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderId", is(listOfOrders.get(1).getOrderId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", is(listOfOrders.get(1).getProductName())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        List<OrderDTO> myObjectsRes = om.readValue(jsonResponse, om.getTypeFactory().constructCollectionType(List.class, OrderDTO.class));

        assertNotNull(myObjectsRes);
        assertEquals(2, myObjectsRes.size());
        assertEquals("Pencil", myObjectsRes.get(0).getProductName());
        assertEquals("Office", myObjectsRes.get(0).getCategory());

    }

    @Test
    void getOrderById_ShouldReturnListOfObjects_ValidReturn() throws Exception {
        OrderDTO order1 = OrderDTO.builder().id(1).orderId(1).productName("Pencil")
                .orderType("cash").quantity(1).price(1).category("Office").build();

        when(orderServiceImpl.getOrderById(1)).thenReturn(order1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order?id="+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(order1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", is(order1.getOrderId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", is(order1.getProductName())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        OrderDTO myObjectsRes = new ObjectMapper().readValue(jsonResponse, OrderDTO.class);

        assertNotNull(myObjectsRes);
        assertEquals("Pencil", myObjectsRes.getProductName());
        assertEquals("Office", myObjectsRes.getCategory());
    }

}
