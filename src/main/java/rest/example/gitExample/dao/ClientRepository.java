package rest.example.gitExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.example.gitExample.resources.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
