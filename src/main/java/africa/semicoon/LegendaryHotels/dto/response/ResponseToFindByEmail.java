package africa.semicoon.LegendaryHotels.dto.response;

import lombok.Data;

@Data
public class ResponseToFindByEmail {
    private String message;
    private String firstName;
    private String lastName;
    private String email;
}
