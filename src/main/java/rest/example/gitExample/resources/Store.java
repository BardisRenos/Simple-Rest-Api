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
@Table(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "store_id")
    private Integer storeId;
    @NotNull
    @Column(name = "store_name")
    private String storeName;
    @NotNull
    @Column(name = "store_city")
    private String storeCity;
    @NotNull
    @Column(name = "store_country")
    private String storeCountry;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "os_fk", referencedColumnName = "id")
    private List<Order> orders;

    /**
     * The constructor
     * @param id The id of the Store table
     * @param storeId The id of the Store
     * @param storeName The name of the Store
     * @param storeCity The city of the Store
     * @param storeCountry The country of the Store
     */
    public Store(Integer id, Integer storeId, String storeName, String storeCity, String storeCountry) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.storeCountry = storeCountry;
    }
}
