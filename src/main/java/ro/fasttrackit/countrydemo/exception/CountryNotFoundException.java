package ro.fasttrackit.countrydemo.exception;

import java.util.UUID;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(UUID id) {
        super("Country with id: " + id + " doesn't exist");
    }

}
