package rest.example.gitExample.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Order;

import java.util.Optional;
@Qualifier("Order")
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findById(Integer id);
}
