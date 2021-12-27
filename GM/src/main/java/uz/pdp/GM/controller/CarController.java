package uz.pdp.GM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.GM.entity.AutoShop;
import uz.pdp.GM.entity.Car;
import uz.pdp.GM.entity.GM;
import uz.pdp.GM.repository.AutoShopRepository;
import uz.pdp.GM.repository.CarRepository;
import uz.pdp.GM.transfer.CarDto;
import uz.pdp.GM.transfer.GMDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    AutoShopRepository autoShopRepository;

    @PostMapping
    public String save(@RequestBody CarDto carDto) {
        boolean exists = carRepository.existsByModelAndYearAndPrice(carDto.getModel(), carDto.getYear(), carDto.getPrice());
        boolean id = autoShopRepository.existsById(carDto.getAutoshopId());
        if (exists && id) return "Already exist";

        Optional<AutoShop> byId = autoShopRepository.findById(carDto.getAutoshopId());
        if (!byId.isPresent()) {
            return "Autoshop not found id";
        }

        AutoShop autoShop = byId.get();

        Car car = new Car(null, carDto.getModel(), carDto.getYear(), carDto.getPrice(), autoShop);
        carRepository.save(car);
        return "saved";
    }

    @GetMapping
    public List<Car> get() {
        List<Car> carList = carRepository.findAll();
        return carList;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (!carRepository.existsById(id)) {
            return "not found id";
        }
        carRepository.deleteById(id);
        return "deleted";
    }


    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody CarDto carDto) {
        if (!carRepository.existsById(id)) {
            return "not found id ";
        }
        Optional<AutoShop> byId = autoShopRepository.findById(carDto.getAutoshopId());
        if (!byId.isPresent()) {
            return "Autoshop not found id";
        }

        AutoShop autoShop = byId.get();

        Car car = new Car(null, carDto.getModel(), carDto.getYear(), carDto.getPrice(), autoShop);
        carRepository.save(car);
        return "editing";

    }

}
