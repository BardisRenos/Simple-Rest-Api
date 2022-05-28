package rest.example.gitExample.resources;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @Column(name = "client_id", updatable = false, nullable = false)
    private Integer clientId;
    @NotNull
    @Column(name = "client_name")
    private String clientName;
    @NotNull
    @Column(name = "client_last_name")
    private String clientLastName;
    @NotNull
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "oc_fk", referencedColumnName = "client_id")
    private List<Order> orders;

    /**
     * Default constructor
     * @param clientId The id of a Client
     * @param clientName The first name of a Client
     * @param clientLastName The last name of a Client
     * @param address The address of a Client
     * @param phoneNumber The phone number of a Client
     */
    public Client(Integer clientId, String clientName, String clientLastName, String address, String phoneNumber) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
