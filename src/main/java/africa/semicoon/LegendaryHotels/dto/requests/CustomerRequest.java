package africa.semicoon.LegendaryHotels.dto.requests;

import lombok.Data;

@Data
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
