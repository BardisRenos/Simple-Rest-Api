package rest.example.gitExample.service.declaration;


import rest.example.gitExample.dto.StoreDTO;
import rest.example.gitExample.dto.StoreOrdersDTO;
import rest.example.gitExample.exception.StoreNotFoundException;

import java.util.List;

public interface StoreService {

    StoreDTO getStoreById(Integer id) throws StoreNotFoundException;
    List<StoreDTO> getAllStores();
    StoreOrdersDTO getStoreAndOrders(String storeName) throws StoreNotFoundException;
    StoreDTO saveStore(StoreDTO storeDTO);
}
