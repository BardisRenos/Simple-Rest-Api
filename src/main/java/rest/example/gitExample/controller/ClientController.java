package rest.example.gitExample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.dto.ClientOrdersDTO;
import rest.example.gitExample.exception.ClientNotFoundException;
import rest.example.gitExample.service.ClientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @GetMapping(value = "clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientServiceImpl.getClients();
    }

    @GetMapping(value = "client")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@RequestParam(value = "id") Integer id) throws ClientNotFoundException {
        return clientServiceImpl.getClientById(id);
    }

    @GetMapping(value = "client/clientLastName/{clientLastName}")
    @ResponseStatus(HttpStatus.OK)
    public ClientOrdersDTO getClientWithOrders(@PathVariable(value = "clientLastName") String clientLastName) throws ClientNotFoundException {
        return clientServiceImpl.getClientOrdersByLastName(clientLastName);
    }

    @GetMapping(value = "client/lastName")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientByLastName(@RequestParam(value = "clientLastName") String clientLastName) throws ClientNotFoundException {
        return clientServiceImpl.getClientByLastName(clientLastName);
    }
}
