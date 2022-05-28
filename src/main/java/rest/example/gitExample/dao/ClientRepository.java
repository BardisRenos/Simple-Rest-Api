package rest.example.gitExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Client;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findById(Integer id);

    @Query("select distinct p from Client p join fetch p.orders o where p.clientLastName = :clientLastName")
    Optional<Client> findClientAndOrdersByLastName(@Param("clientLastName") String clientLastName);

    @Query("select p from Client p where p.clientLastName = :clientLastName")
    Optional<Client> findClientByLastName(@Param("clientLastName") String clientLastName);

}
