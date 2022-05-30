package rest.example.gitExample.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.example.gitExample.dao.ClientRepository;
import rest.example.gitExample.dao.OrderRepository;
import rest.example.gitExample.dao.StoreRepository;
import rest.example.gitExample.resources.Client;
import rest.example.gitExample.resources.Order;
import rest.example.gitExample.resources.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class dbInsertion {

    @Bean
    public CommandLineRunner initDB(OrderRepository orderRepository, ClientRepository clientRepository, StoreRepository storeRepository) {

        return args-> {
            List<Order> orders = new ArrayList<>(Arrays.asList(
                    new Order(1, 121, "Make Up", "Card", 1, 20, "Beauty Product"),
                    new Order(2, 122, "Lipstick", "Cash", 1, 30, "Beauty Product"),
                    new Order(3, 123, "Laptop", "Cash", 1, 1000, "Electronics"),
                    new Order(4, 124, "Towels", "Card", 2, 10, "House Items"),
                    new Order(5, 125, "Shoes", "Card", 1, 200, "Clothes"),
                    new Order(6, 126, "Short", "Card", 2, 80, "Clothes"),
                    new Order(7, 127, "Screen", "Cash", 1, 700, "Electronics"),
                    new Order(8, 128, "Gloves", "Card", 3, 100, "Garden Items"),
                    new Order(9, 129, "shocks", "Cash", 5, 70, "Clothes"),
                    new Order(10, 1210, "Jean", "Card", 1, 180, "Clothes"),
                    new Order(11, 1211, "Keyboard", "Cash", 1, 90, "Electronics"),
                    new Order(12, 1212, "Camera", "Card", 1, 100, "Electronics")
            ));

            List<Client> clients = new ArrayList<>(Arrays.asList(
                    new Client(1, "Renos", "Bardis", "78 Bd du President Wilson, 06061 Juan Les Pins", "06161616161"),
                    new Client(2, "Nikos", "Papadopoulos", "65 Rue du Nice 06000 Nice", "0845885222"),
                    new Client(3, "John", "Pappas", "75 Rue du Cannes 06010 Cannes", "0895123222"),
                    new Client(4, "Francois", "Demi", "23 Rue du San Rafael 06020 SR", "85565123222")
            ));

            Store store1 = new Store(1, 123, "Athens-Store", "Athens", "Greece");
            store1.setOrders(orders.subList(0, 5));
            Store store2 = new Store(2, 124, "Rome-Store", "Rome", "Italy");
            store2.setOrders(orders.subList(5, 10));
            Store store3 = new Store(3, 125, "Paris-Store", "Paris", "France");
            store3.setOrders(orders.subList(10, orders.size()));

            List<Store> stores = Arrays.asList(store1, store2, store3);

            Order order1 = orders.get(2);
            Order order2 = orders.get(6);

            order1.setClients(clients.subList(0, 2));
            order2.setClients(clients.subList(2, clients.size()));

            Client client1 = clients.get(0);
            Client client2 = clients.get(1);
            Client client3 = clients.get(2);

            client1.setOrders(orders.subList(0, 4));
            client2.setOrders(orders.subList(4, 8));
            client3.setOrders(orders.subList(8, orders.size()));

            orderRepository.saveAll(orders);
            clientRepository.saveAll(clients);
            storeRepository.saveAll(stores);

        };
    }
}
