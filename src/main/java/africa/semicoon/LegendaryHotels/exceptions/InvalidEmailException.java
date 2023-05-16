package africa.semicoon.LegendaryHotels.exceptions;

import lombok.Data;

@Data
public class InvalidEmailException extends IllegalArgumentException{

    public InvalidEmailException(String message){
        super(message);
    }
}
