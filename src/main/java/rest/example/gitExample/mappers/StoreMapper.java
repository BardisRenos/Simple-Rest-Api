package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.StoreDTO;
import rest.example.gitExample.dto.StoreOrdersDTO;
import rest.example.gitExample.resources.Store;

@Service
public class StoreMapper {

    /**
     * Convert the Store entity into StoreDTO object
     * @param store The store entity class
     * @return A StoreDTO object
     */
    public static StoreDTO convertEntityToDTO(Store store) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreDTO.class);
    }

    /**
     * Convert the StoreDTO object into Store entity
     * @param storeDTO The storeDTO object
     * @return A Store entity
     */
    public static Store convertDTOtoEntity(StoreDTO storeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storeDTO, Store.class);
    }

    /**
     * Convert the Store entity into StoreOrdersDTO object
     * @param store The store entity
     * @return A storeOrdersDTO object
     */
    public static StoreOrdersDTO convertEntityToStoreOrderDTO(Store store) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreOrdersDTO.class);
    }

}
