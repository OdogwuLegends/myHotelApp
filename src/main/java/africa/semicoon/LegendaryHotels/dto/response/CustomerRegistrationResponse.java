package africa.semicoon.LegendaryHotels.dto.response;

import lombok.Data;

@Data
public class CustomerRegistrationResponse {
    private String firstName;
    private String lastName;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        sb.append("Account creation successful.");
        sb.append("Welcome ").append(firstName).append(" ").append(lastName).append(".");

        return sb.toString();
    }
}
