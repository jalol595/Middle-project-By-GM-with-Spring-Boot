package uz.pdp.GM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.GM.entity.Address;
import uz.pdp.GM.repository.AddressRepository;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public String save(@RequestBody Address address) {
        boolean exists = addressRepository.existsByStreetAndNumber(address.getStreet(), address.getNumber());
        if (exists) return "alredy exist";

        addressRepository.save(address);
        return "Saved";
    }


    @GetMapping
    public List<Address> get() {
        List list = addressRepository.findAll();
        return list;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (!addressRepository.existsById(id)) {
            return "not found";
        }

        addressRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Address address) {
        if (!addressRepository.existsById(id)) {
            return "not editing";
        }

        Address editAddress = new Address();
        editAddress.setStreet(address.getStreet());
        editAddress.setNumber(address.getNumber());
        addressRepository.save(editAddress);
        return "editing";
    }




}
