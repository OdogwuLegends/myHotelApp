package africa.semicoon.LegendaryHotels.dto.requests;

import lombok.Data;

@Data
public class RequestsForCustomers {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
