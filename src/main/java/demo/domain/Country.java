package demo.domain;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    private String code;
    private String name;
    private String localname;
    private String continent;
    private double surfacearea;
    private Short indepyear;
    private int population;
    private Float lifeexpectancy;
  
	@Column(name = "GNP")
    private double GNP;
    @Column(name = "GNPOld")
    private Double GNPOld;
    private String governmentform;
    private String headofstate;
    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital")
    private City capital;
    private String code2;

    public Country() {
    }

    public Country(String code, String name, String localname, String continent, double surfaceArea, short indepyear, int population) {
        this.code = code;
        this.name = name;
        this.localname = localname;
        this.continent = continent;
        this.surfacearea = surfaceArea;
        this.indepyear = indepyear;
        this.population = population;
        
      
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
    	name=name.replaceAll("Ã�", "i");
    	name=name.replaceAll("Ã­", "i");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(double surfacearea) {
        this.surfacearea = surfacearea;
    }

    public Short getIndepyear() {
        return indepyear;
    }

    public void setIndepyear(Short indepyear) {
    	
        this.indepyear = indepyear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }

    public Float getLifeexpectancy() {
    	if(lifeexpectancy==null)
    		return (float) 0;
    	else
        return lifeexpectancy;
    }

    public void setLifeexpectancy(Float lifeexpectancy) {
        this.lifeexpectancy = lifeexpectancy;
    }

    public double getGNP() {
        return GNP;
    }

    public void setGNP(double GNP) {
        this.GNP = GNP;
    }

    public Double getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(Double GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getGovernmentform() {
        return governmentform;
    }

    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform;
    }

    public String getHeadofstate() {
        return headofstate;
    }

    public void setHeadofstate(String headofstate) {
        this.headofstate = headofstate;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

}
