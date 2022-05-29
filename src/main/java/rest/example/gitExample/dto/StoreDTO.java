package rest.example.gitExample.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private Integer id;
    private Integer storeId;
    private String storeName;
    private String storeCity;
    private String storeCountry;
}
