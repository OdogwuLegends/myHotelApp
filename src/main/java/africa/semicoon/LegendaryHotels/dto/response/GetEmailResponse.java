package africa.semicoon.LegendaryHotels.dto.response;

import africa.semicoon.LegendaryHotels.models.Customer;
import lombok.Data;

@Data
public class GetEmailResponse {
    private String message;
    private String firstName;
    private String lastName;
    private String email;
}
