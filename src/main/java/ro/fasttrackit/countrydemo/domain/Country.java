package ro.fasttrackit.countrydemo.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Country {
    private final UUID id;
    private final String name;
    private final String capital;
    private final long population;
    private final long area;
    private final Continent continent;
    private final List<String> neighbours;

    public Country(String name, String capital, long population, long area, Continent continent, List<String> neighbours) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public long getArea() {
        return area;
    }

    public Continent getContinent() {
        return continent;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && area == country.area && Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(capital, country.capital) && continent == country.continent && Objects.equals(neighbours, country.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capital, population, area, continent, neighbours);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", continent=" + continent +
                ", neighbours=" + neighbours +
                '}';
    }
}
