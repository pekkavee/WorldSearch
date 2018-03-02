package demo;

import demo.domain.City;
import demo.domain.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

	
    @Query("SELECT c FROM Country c WHERE c.capital.name = :kaupunki")
    List<Country> haePaakaupunginNimella(@Param("kaupunki") String capital);
    @Query(value = "SELECT * FROM Country  WHERE population >= :population ORDER BY population LIMIT :limiitti", nativeQuery = true)
    List<Country> haePopulaationMaarallaLimit(@Param("population") int populaatio, @Param("limiitti") int limiitti);
    @Transactional
    @Modifying
   @Query("UPDATE Country c SET c.name = :name WHERE c.code = :code")
  int updateCountryName(@Param("name")String name, @Param("code")String code);
    @Transactional
    @Modifying
   @Query("UPDATE Country c SET c.localname = :localname WHERE c.code = :code")
  int updateCountryLocalName(@Param("localname")String localname, @Param("code")String code);
    @Transactional
    @Modifying
   @Query("UPDATE Country c SET c.population = :population WHERE c.code = :code")
  int updateCountryPopulation(@Param("population")int population, @Param("code")String code);
    @Transactional
    @Modifying
   @Query("UPDATE Country c SET c.headofstate = :headofstate WHERE c.code = :code")
  int updateCountryHos(@Param("headofstate")String headofstate, @Param("code")String code);
    
}
