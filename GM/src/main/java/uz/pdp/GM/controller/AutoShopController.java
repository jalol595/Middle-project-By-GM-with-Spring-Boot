package uz.pdp.GM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.GM.entity.Address;
import uz.pdp.GM.entity.AutoShop;
import uz.pdp.GM.entity.GM;
import uz.pdp.GM.repository.AddressRepository;
import uz.pdp.GM.repository.AutoShopRepository;
import uz.pdp.GM.repository.GMRepository;
import uz.pdp.GM.transfer.AutoShopDto;
import uz.pdp.GM.transfer.GMDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autoshop")
public class AutoShopController {

    @Autowired
    AutoShopRepository autoShopRepository;

    @Autowired
    GMRepository gmRepository;

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public String save(@RequestBody AutoShopDto autoShopDto) {
        boolean byName = autoShopRepository.existsByName(autoShopDto.getName());
        boolean byId = gmRepository.existsById(autoShopDto.getGmId());
        boolean streetAndNumber = addressRepository.existsByStreetAndNumber(autoShopDto.getStreet(), autoShopDto.getNumber());
        if (byName && byId && streetAndNumber) return "already exist";

        Optional<GM> repository = gmRepository.findById(autoShopDto.getGmId());
        if (!repository.isPresent()) {
            return "Gm not found";
        }


        GM gm = repository.get();

        Address address = new Address(null, autoShopDto.getStreet(), autoShopDto.getNumber());

        Address save = addressRepository.save(address);

        AutoShop autoShop = new AutoShop(null, autoShopDto.getName(), gm, save);
        autoShopRepository.save(autoShop);

        return "saved";

    }

    @GetMapping
    public List<AutoShop> get(){
        List<AutoShop> list = autoShopRepository.findAll();
        return list;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if (!autoShopRepository.existsById(id)){
            return "not found";
        }

        autoShopRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody AutoShopDto autoShopDto){
        if (!autoShopRepository.existsById(id)){
            return "not editing";
        }

        Optional<GM> repository = gmRepository.findById(autoShopDto.getGmId());
        if (!repository.isPresent()) {
            return "Gm not found";
        }


        GM gm = repository.get();


        Address address = new Address(null, autoShopDto.getStreet(), autoShopDto.getNumber());


        Address save = addressRepository.save(address);

        AutoShop autoShop = new AutoShop(null, autoShopDto.getName(), gm, save);
        autoShopRepository.save(autoShop);

        return "edit";
    }
}
