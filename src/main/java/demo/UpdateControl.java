package demo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/update")
public class UpdateControl {
	@Autowired
    CountryRepository countryRepository;
	 @GetMapping
	    public String indeksi(Model model) {
	    
	        return "update";
	    }

}
