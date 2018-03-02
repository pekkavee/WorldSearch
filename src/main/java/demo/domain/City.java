package demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class City {
    @Id
    private int id;
    private String name;
    @OneToOne
    @JoinColumn(name="countrycode")
    private Country country;
    private String district;
    private int population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        name=name.replaceAll("Ã¶", "ö");
        name=name.replaceAll("Ã–", "Ö");
        name=name.replaceAll("Ã¤", "ä");
        name=name.replaceAll("Ã¥", "å");
        name=name.replaceAll("Ã…", "Å");
        name=name.replaceAll("Ã¦", "æ");
        name=name.replaceAll("Ã¸", "ø");
        name=name.replaceAll("Ã­", "i");
        
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
    	country.getName();
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
    	 district=district.replaceAll("Ã¶", "ö");
         district=district.replaceAll("Ã–", "Ö");
         district=district.replaceAll("Ã¤", "ä");
         district=district.replaceAll("Ã¥", "å");
         district=district.replaceAll("Ã¸", "ø");
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;
        return country.equals(city.country);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
