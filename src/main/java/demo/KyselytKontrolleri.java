package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Infinispan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import demo.domain.City;
import demo.domain.Country;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/kyselyt")
public class KyselytKontrolleri {
	private int numero;
	private int numero2;
	private int yht;
	
	
	public int getYht() {
		return yht;
	}

	public void setYht(int yht) {
		this.yht = yht;
	}

	public int getNumero2() {
		return numero2;
	}

	public void setNumero2(int numero2) {
		this.numero2 = numero2;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	private String kaupunki;
	
    public String getKaupunki() {
		return kaupunki;
	}

	public void setKaupunki(String kaupunki) {
		this.kaupunki = kaupunki;
	}

	@Autowired
    CountryRepository countryRepository;
    @Autowired
    CityRepository cityRepository;

    @GetMapping
    public String indeksi(Model model) {
        Map<URI, String> osoitteet = new HashMap<>();
        osoitteet.put(teeURI("pkhaku"), "Hae pääkaupungin nimellä");
        osoitteet.put(teeURI("pophaku"), "Hae populaation määrällä (param: kaupunki)");
        osoitteet.put(teeURI("pkmukaan"), "Hae kaupungit pääkaupungin nimellä (param: kaupunki)");
        model.addAttribute("osoitteet", osoitteet);
        return "maaindeksi";
    }
    
 @GetMapping("/update")
  public String maaMuokkaa(@RequestParam(name = "name",defaultValue = "") String name, 
		  @RequestParam(name = "localname",defaultValue = "") String localname, 
		  @RequestParam(name = "population",defaultValue = "") int population, 
		  @RequestParam(name= "headofstate",defaultValue="") String headofstate,
		  @RequestParam(name = "code", defaultValue = "") String code, Model model) {
     countryRepository.updateCountryName(name, code);
     countryRepository.updateCountryLocalName(localname, code);
     countryRepository.updateCountryPopulation(population, code);
     countryRepository.updateCountryHos(headofstate, code);
     return "maaindeksi";
    }


    @GetMapping("/pkhaku")
    public String maaKaupunginNimella(Model m) {
        m.addAttribute("maat", countryRepository.haePaakaupunginNimella(kaupunki));
    
        return "maalista";
    }

    @GetMapping("/pophaku")
    public String maaPopulaationMaaralla(@RequestParam(name = "kaupunki", defaultValue = "") String nimi, Model model) {
    List<Country> maat = new ArrayList<>();
    List<Country> maatkaikki = new ArrayList<>();
    
  
    for(Country c : countryRepository.findAll()) {
    	maatkaikki.add(c);
    	if(c.getName().toLowerCase().equals(nimi.toLowerCase())||c.getLocalname().toLowerCase().equals(nimi.toLowerCase()))
        maat.add(c);
    }
    KyselytKontrolleri kontrolleri = new KyselytKontrolleri();
    List<Country> maatkaikki2 = new ArrayList<>(maatkaikki);
    Collections.sort(maatkaikki, (a, b) -> a.getPopulation() > b.getPopulation() ? -1 : a.getPopulation() == b.getPopulation() ? 0 : 1);
 //Collections.sort(maatkaikki2, (a, b) -> a.getLifeexpectancy() > b.getLifeexpectancy() ? -1 : a.getLifeexpectancy() == b.getLifeexpectancy() ? 0 : 1);
    Collections.sort(maatkaikki2, (a, b) -> a.getLifeexpectancy() > b.getLifeexpectancy() ? -1 : a.getLifeexpectancy() == b.getLifeexpectancy() ? 0 : 1);
    if(maat.size()>0) {
    int num =maatkaikki.indexOf(maat.get(0));
  int num2=maatkaikki2.indexOf(maat.get(0));
    kontrolleri.setNumero(num+1);
   kontrolleri.setNumero2(num2+1);
   kontrolleri.setYht(maatkaikki2.size());
    
    }
    
    	model.addAttribute("numero", kontrolleri);
    	
        model.addAttribute("maat", maat);
  
        return "maalista";
    }
    @GetMapping("/pophaku2")
    public String maaMuokattavaksi(@RequestParam(name = "kaupunki", defaultValue = "") String nimi, Model model) {
   //     public String maaMuokattavaksi(@RequestParam(name = "kaupunki", defaultValue = "") String nimi, Model model) {

    	List<Country> maat = new ArrayList<>();
   
    
  
    for(Country c : countryRepository.findAll()) {
    	
    	if(c.getName().toLowerCase().equals(nimi.toLowerCase())||c.getLocalname().toLowerCase().equals(nimi.toLowerCase()))
        maat.add(c);
    }
    	
        model.addAttribute("maat", maat);
  
       return "updateform";
    }
    
  
    


    @GetMapping("/pkmukaan")
    public String kaupungitPaakaupunginMukaan(@RequestParam(name = "kaupunki", defaultValue = "") String nimi, Model model) {
    
  model.addAttribute("kaupungit", cityRepository.haeMaanKaupungitPaakaupunginNimella(nimi));
   
        return "kaupunkilista";
    }

    private URI teeURI(String osoite) {
        return UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").port(8080)
                .pathSegment("kyselyt", osoite).build().toUri();
    }
    
}
