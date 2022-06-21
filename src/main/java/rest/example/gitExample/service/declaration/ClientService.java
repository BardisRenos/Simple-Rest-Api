package rest.example.gitExample.service.declaration;

import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.dto.ClientOrdersDTO;
import rest.example.gitExample.exception.ClientNotFoundException;

import java.util.List;

/**
 * Client Service Interface
 */
public interface ClientService {

    List<ClientDTO> getClients();
    ClientDTO getClientById(Integer id) throws ClientNotFoundException;
    ClientOrdersDTO getClientOrdersByLastName(String clientLastName) throws ClientNotFoundException;
    ClientDTO getClientByLastName(String lastName) throws ClientNotFoundException;
    ClientDTO saveClient(ClientDTO clientDTO);
}
