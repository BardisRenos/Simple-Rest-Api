package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.resources.Client;

@Service
public class ClientMapper {

    public static ClientDTO convertEntityToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }
}
