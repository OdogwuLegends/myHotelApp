package africa.semicoon.LegendaryHotels.dto.response;

import lombok.Data;

@Data
public class AdminRegistrationResponse {
    private String firstName;
    private String lastName;
    private int adminLoginCode;


    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer();

        sb.append("Hello ").append(firstName).append(".");
        sb.append("Your account has been created as an Admin.");
        sb.append("Your log in code is: ").append(adminLoginCode);

        return sb.toString();
    }
}
