package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.ClientDTO;
import rest.example.gitExample.resources.Client;

@Service
public class ClientMapper {

    /**
     * Convert the Client entity into ClientDTO object
     * @param client The client entity class
     * @return A ClientDTO object
     */
    public static ClientDTO convertEntityToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }

    /**
     * Convert the ClientDTO object into Client entity
     * @param clientDTO The ClientDTO object
     * @return A Client entity
     */
    public static Client convertDTOToEntity(ClientDTO clientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientDTO, Client.class);
    }
}
