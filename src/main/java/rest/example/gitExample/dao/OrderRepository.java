package rest.example.gitExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


}
