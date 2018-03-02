package demo;

import demo.domain.Country;

import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class Kontrolleri {

    @Autowired
    private CountryRepository maarepo;
    
    @GetMapping("/haemaat")
    public String maahaku2(Model model) {
//        List<Country> maat = new ArrayList<>();
//        for(Country c : maarepo.findAll()) {
//            maat.add(c);
//        }
        model.addAttribute("maat", maarepo.findAll());
        return "haemaa";
    }

    @GetMapping("/maat")
    public String maahaku(Model model) {
//        List<Country> maat = new ArrayList<>();
//        for(Country c : maarepo.findAll()) {
//            maat.add(c);
//        }
        model.addAttribute("maat", maarepo.findAll());
        return "maalista";
    }
    @GetMapping("/maat2")
    public String maahaku3(Model model) {
       List<Country> maat = new ArrayList<>();
       for(Country c : maarepo.findAll()) {
    	   maat.add(c);
     }
       Collections.sort(maat, (a, b) -> a.getPopulation() > b.getPopulation() ? -1 : a.getPopulation() == b.getPopulation() ? 0 : 1);
       List<Country> maat3 = new ArrayList<>();
   
       model.addAttribute("maat", maat);
        return "maalista";
    }

    // http://localhost:8080/maasivut?page=3&size=40
    @GetMapping("/maasivut")
    public String sivutetutmaat(Pageable pageable,
                                Model model){
   
       Page<Country> sivu = maarepo.findAll(pageable);
         
        List<Country> maat = sivu.getContent();
        

        model.addAttribute("maat", maat);
        model.addAttribute("sivuja", sivu.getTotalPages());
        model.addAttribute("nykyinen", pageable.getPageNumber()); // tai sivu.getNumber()
        model.addAttribute("sivuolio", sivu);
        return "maalista";
    }
   


}