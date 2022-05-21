package rest.example.gitExample.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.example.gitExample.dao.ClientRepository;
import rest.example.gitExample.dao.OrderRepository;
import rest.example.gitExample.resources.Client;
import rest.example.gitExample.resources.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class dbInsertion {

    @Bean
    public CommandLineRunner initDB(OrderRepository orderRepository, ClientRepository clientRepository) {

        return args-> {
            List<Order> orders = new ArrayList<>(Arrays.asList(
                    new Order(123, "Make Up", "Card", 1, 20, "Beauty Product"),
                    new Order(124, "Lipstick", "Cash", 1, 30, "Beauty Product"),
                    new Order(125, "Laptop", "Cash", 1, 1000, "Electronics"),
                    new Order(126, "Towels", "Card", 2, 10, "House Items")
            ));

            List<Client> clients = new ArrayList<>(Arrays.asList(
                    new Client(1, "Renos", "Bardis", "78 Bd du President Wilson, 06061 Juan Les Pins", "06161616161"),
                    new Client(2, "Nikos", "Papadopoulos", "65 Rue du Nice 06000 Nice", "0845885222"),
                    new Client(3, "John", "Pappas", "75 Rue du Cannes 06010 Cannes", "0895123222")
            ));

            orderRepository.saveAll(orders);
            clientRepository.saveAll(clients);
        };
    }
}
