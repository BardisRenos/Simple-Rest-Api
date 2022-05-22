package rest.example.gitExample.service.declaration;

import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.exception.ClientNotFoundException;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClients();
    ClientDTO getClientById(Integer id) throws ClientNotFoundException;
}
