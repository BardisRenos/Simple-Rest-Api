package rest.example.gitExample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.example.gitExample.dto.StoreDTO;
import rest.example.gitExample.dto.StoreOrdersDTO;
import rest.example.gitExample.exception.StoreNotFoundException;
import rest.example.gitExample.service.StoreServiceImpl;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StoreController {

    private final StoreServiceImpl storeServiceimpl;

    @GetMapping(value = "stores")
    @ResponseStatus(HttpStatus.OK)
    public List<StoreDTO> getAllStores() {
        return storeServiceimpl.getAllStores();
    }

    @GetMapping(value = "store")
    @ResponseStatus(HttpStatus.OK)
    public StoreDTO getStoreById(@RequestParam(value = "id") Integer id) throws StoreNotFoundException {
        return storeServiceimpl.getStoreById(id);
    }

    @GetMapping(value = "storeName")
    @ResponseStatus(HttpStatus.OK)
    public StoreOrdersDTO getStoreByStoreName(@RequestParam(value = "name") String name) throws StoreNotFoundException {
        return storeServiceimpl.getStoreAndOrders(name.toLowerCase(Locale.ROOT));
    }
}
