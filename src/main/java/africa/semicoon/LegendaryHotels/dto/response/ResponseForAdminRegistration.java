package africa.semicoon.LegendaryHotels.dto.response;

import lombok.Data;

@Data
public class ResponseForAdminRegistration {
    private String firstName;
    private String lastName;
    private int adminLoginCode;


    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer();

        sb.append("\nHello ").append(firstName).append(".");
        sb.append("\nYour account has been created as an Admin.");
        sb.append("\nYour log in code is: ").append(adminLoginCode);

        return sb.toString();
    }
}
