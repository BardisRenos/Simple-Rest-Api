package rest.example.gitExample.resources;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Builder
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", updatable = false, nullable = false)
    private Integer clientId;
    @NotNull
    @Column(name = "company_name")
    private String clientName;
    @NotNull
    @Column(name = "company_last_name")
    private String clientLastName;
    @NotNull
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
}
