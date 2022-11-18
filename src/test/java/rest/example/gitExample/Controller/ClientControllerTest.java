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
import rest.example.gitExample.controller.ClientController;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.service.ClientServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientServiceImpl clientServiceImpl;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.clientController = new ClientController(this.clientServiceImpl);
    }

    @Test
    void getAllClients_ShouldReturnListOfObjects_ValidReturn() throws Exception {
        ClientDTO clientDTO1 = ClientDTO.builder().clientName("John").clientLastName("Smith")
                .address("52 Boulevard du Paris").phoneNumber("0369874556").build();

        ClientDTO clientDTO2 = ClientDTO.builder().clientName("Nick").clientLastName("Conway")
                .address("57 Boulevard du Cannes").phoneNumber("05989444").build();

        List<ClientDTO>  listOfClients = Arrays.asList(clientDTO1, clientDTO2);

        when(clientServiceImpl.getClients()).thenReturn(listOfClients);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(listOfClients)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(listOfClients.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clientName", is(listOfClients.get(0).getClientName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clientLastName", is(listOfClients.get(0).getClientLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientName", is(listOfClients.get(1).getClientName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientLastName", is(listOfClients.get(1).getClientLastName())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        List<ClientDTO> myObjectsRes = om.readValue(jsonResponse, om.getTypeFactory().constructCollectionType(List.class, ClientDTO.class));

        assertNotNull(myObjectsRes);
        assertEquals(2, myObjectsRes.size());
        assertEquals("John", myObjectsRes.get(0).getClientName());
        assertEquals("Smith", myObjectsRes.get(0).getClientLastName());

    }

    @Test
    void getClientById_ShouldReturnListOfObjects_ValidReturn() throws Exception {
        ClientDTO clientDTO = ClientDTO.builder().clientName("John").clientLastName("Smith")
                .address("52 Boulevard du Paris").phoneNumber("0369874556").build();

        when(clientServiceImpl.getClientById(1)).thenReturn(clientDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client?id="+1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName", is(clientDTO.getClientName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientLastName", is(clientDTO.getClientLastName())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ClientDTO myObjectsRes = new ObjectMapper().readValue(jsonResponse, ClientDTO.class);

        assertNotNull(myObjectsRes);
        assertEquals("John", myObjectsRes.getClientName());
        assertEquals("Smith", myObjectsRes.getClientLastName());
    }
}
