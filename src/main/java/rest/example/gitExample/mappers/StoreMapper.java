package rest.example.gitExample.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dto.StoreDTO;
import rest.example.gitExample.dto.StoreOrdersDTO;
import rest.example.gitExample.resources.Store;

@Service
public class StoreMapper {

    public static StoreDTO convertEntityToDTO(Store store) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreDTO.class);
    }

    public static Store convertDTOtoEntity(StoreDTO storeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storeDTO, Store.class);
    }


    public static StoreOrdersDTO convertEntityToStoreOrderDTO(Store store) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreOrdersDTO.class);
    }

}
