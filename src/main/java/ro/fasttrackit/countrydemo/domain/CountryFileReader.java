package ro.fasttrackit.countrydemo.domain;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CountryFileReader {
    private final String sourceFile;

    public CountryFileReader() {
        this.sourceFile = "src/main/resources/static/countries.txt";
    }

    public List<Country> readCountries() {
        try {
            return Files.lines(Path.of(sourceFile))
                    .map(this::readCountry)
                    .collect(toList());
        } catch (IOException e) {
            System.err.println("Could not read from file " + sourceFile);
        }

        return List.of();
    }

    private Country readCountry(String line) {
        String[] countryInfo = line.split("\\|");
        return new Country(
                countryInfo[0],
                countryInfo[1],
                Long.parseLong(countryInfo[3]),
                Long.parseLong(countryInfo[2]),
                Continent.of(countryInfo[4]).orElse(null),
                countryInfo.length > 5 ? parseNeighbours(countryInfo[5]) : List.of()
        );
    }

    private List<String> parseNeighbours(String neighbours) {
        if (neighbours != null) {
            return Arrays.stream(neighbours.split("~"))
                    .collect(toList());
        }

        return List.of();
    }
}
