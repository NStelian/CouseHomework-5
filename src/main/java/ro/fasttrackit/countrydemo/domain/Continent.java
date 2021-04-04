package ro.fasttrackit.countrydemo.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum Continent {
    AFRICA,
    AMERICAS,
    ASIA,
    EUROPE,
    OCEANIA;

    public static Optional<Continent> of(String continentName) {
        return Stream.of(values())
                .filter(val -> val.name().equalsIgnoreCase(continentName))
                .findFirst();
    }
}
