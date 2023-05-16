package africa.semicoon.LegendaryHotels.models;

import lombok.Data;

@Data
public class Admin {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int adminLoginCode;
}
