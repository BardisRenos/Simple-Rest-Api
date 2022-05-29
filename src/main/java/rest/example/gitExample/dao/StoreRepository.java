package rest.example.gitExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Store;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    Optional<Store> findById(Integer id);

    @Query("select distinct p from Store p join fetch p.orders c where lower(p.storeName) like %:storeName%")
    Optional<Store> findStoreAndOrders(@Param("storeName") String storeName);

}
