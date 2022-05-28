package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.ClientOrdersDTO;
import rest.example.gitExample.resources.Client;

@Service
public class ClientOrdersMapper {

    public static ClientOrdersDTO convertEntityToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientOrdersDTO.class);
    }
}
