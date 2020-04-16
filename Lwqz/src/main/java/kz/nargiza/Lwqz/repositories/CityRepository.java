package kz.nargiza.Lwqz.repositories;

import kz.nargiza.Lwqz.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityById(Long  id);

    Optional<City> findCityByName(String name);
}
