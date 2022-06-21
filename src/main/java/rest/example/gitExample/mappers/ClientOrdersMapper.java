package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.ClientOrdersDTO;
import rest.example.gitExample.resources.Client;

@Service
public class ClientOrdersMapper {

    /**
     * Convert Client entity into ClientOrdersDTO object
     * @param client The client entity
     * @return A ClientOrdersDTO object
     */
    public static ClientOrdersDTO convertEntityToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientOrdersDTO.class);
    }
}
