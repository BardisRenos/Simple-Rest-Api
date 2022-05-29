package rest.example.gitExample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rest.example.gitExample.dao.StoreRepository;
import rest.example.gitExample.dto.StoreDTO;
import rest.example.gitExample.dto.StoreOrdersDTO;
import rest.example.gitExample.exception.StoreNotFoundException;
import rest.example.gitExample.mappers.StoreMapper;
import rest.example.gitExample.service.declaration.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public StoreDTO getStoreById(Integer id) throws StoreNotFoundException {
        return storeRepository.findById(id).map(StoreMapper::convertEntityToDTO).orElseThrow(()-> new StoreNotFoundException(String.format("The store not found with the id %s", id)));
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(StoreMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public StoreOrdersDTO getStoreAndOrders(String name) throws StoreNotFoundException {
        return storeRepository.findStoreAndOrders(name).map(StoreMapper::convertEntityToStoreOrderDTO).orElseThrow(()-> new StoreNotFoundException(String.format("The store is not found with the store city %s", name)));
    }
}
