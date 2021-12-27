package uz.pdp.GM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.GM.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

    boolean existsByModelAndYearAndPrice(String model, Integer year, Integer price);

}
