package rest.example.gitExample.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String clientName;
    private String clientLastName;
    private String address;
    private String phoneNumber;
}
