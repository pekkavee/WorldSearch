package demo;

import demo.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends PagingAndSortingRepository<City, String> {
    @Query("SELECT c FROM City c WHERE c.country IN (SELECT c2 FROM Country c2 WHERE c2.name LIKE CONCAT('%', :kaupunki, '%') OR c2.localname LIKE CONCAT('%', :kaupunki, '%'))")
    List<City> haeMaanKaupungitPaakaupunginNimella(@Param("kaupunki") String capital);

}
//@Query("SELECT c FROM City c WHERE c.country IN (SELECT c2 FROM Country c2 WHERE c2.name LIKE CONCAT('%', :kaupunki, '%'))")
