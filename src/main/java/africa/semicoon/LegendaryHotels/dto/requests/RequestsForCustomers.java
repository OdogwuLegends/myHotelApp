package africa.semicoon.LegendaryHotels.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestsForCustomers {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
