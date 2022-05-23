package rest.example.gitExample.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rest.example.gitExample.dao.ClientRepository;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.exception.ClientNotFoundException;
import rest.example.gitExample.resources.Client;
import rest.example.gitExample.service.ClientServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ClientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ClientServiceImplTest {

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @BeforeEach
    void setup() {
        this.clientRepository = mock(ClientRepository.class);
        this.clientServiceImpl = new ClientServiceImpl(this.clientRepository);
    }

    @Test
    void getClients_ShouldReturnListObjects_ValidReturn() {
        Client client1 = Client.builder().clientId(1).clientName("Renos").clientLastName("Bardis")
                .address("78 Bd du President").phoneNumber("0211548445").build();

        Client client2 = Client.builder().clientId(2).clientName("Nikos").clientLastName("Papas")
                .address("18 Bd du President").phoneNumber("0777748445").build();

        List<Client> listOfClients = new ArrayList<>(Arrays.asList(client1, client2));

        when(clientRepository.findAll()).thenReturn(listOfClients);

        List<ClientDTO> clientDTOList = clientServiceImpl.getClients();
        assertAll("Return a list of OrderDTO",
                ()->assertEquals(2, clientDTOList.size()),
                ()->assertEquals("Renos", clientDTOList.get(0).getClientName()),
                ()->assertEquals("Bardis", clientDTOList.get(0).getClientLastName()),
                ()->assertEquals("Nikos", clientDTOList.get(1).getClientName()),
                ()->assertEquals("Papas", clientDTOList.get(0).getClientLastName()));
    }

    @Test
    void getClientById_ShouldReturnObjects_ValidReturn() throws ClientNotFoundException {
        Client client = Client.builder().clientId(1).clientName("Renos").clientLastName("Bardis")
                .address("78 Bd du President").phoneNumber("0211548445").build();

        when(clientRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(client));

        ClientDTO clientDTO = clientServiceImpl.getClientById(1);
        assertAll("Return a list of OrderDTO",
                ()->assertEquals("Renos", clientDTO.getClientName()),
                ()->assertEquals("Bardis", clientDTO.getClientLastName()));
    }
}
