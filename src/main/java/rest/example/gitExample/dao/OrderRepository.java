package rest.example.gitExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findById(Integer id);

    @Query("select distinct p from Order p join fetch p.clients c where lower(p.category) like %:category%")
    List<Order> findOrdersByClients(@Param("category") String category);
}
