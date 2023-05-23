package africa.semicoon.LegendaryHotels.dto.response;

import africa.semicoon.LegendaryHotels.models.Customer;
import lombok.Data;

@Data
public class ResponseForCustomerRegistration {
    private String firstName;
    private String lastName;
    private String message;
    private String email;
    private String password;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        sb.append("\nAccount creation successful.");
        sb.append("\nWelcome, ").append(firstName).append(" ").append(lastName).append(".\n");

        return sb.toString();
    }
}
