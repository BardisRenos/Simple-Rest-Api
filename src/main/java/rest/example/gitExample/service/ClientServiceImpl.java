package rest.example.gitExample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dao.ClientRepository;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.exception.ClientNotFoundException;
import rest.example.gitExample.mappers.ClientMapper;
import rest.example.gitExample.service.declaration.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Integer id) throws ClientNotFoundException {
        return clientRepository.findById(id).map(ClientMapper::convertEntityToDTO).orElseThrow(()-> new ClientNotFoundException(String.format("The Client was not found with ID: %s", id)));
    }


}
