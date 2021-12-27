package uz.pdp.GM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.GM.entity.Address;
import uz.pdp.GM.entity.GM;
import uz.pdp.GM.repository.AddressRepository;
import uz.pdp.GM.repository.GMRepository;
import uz.pdp.GM.transfer.GMDto;

import java.util.List;

@RestController
@RequestMapping("/gm")
public class GMController {

    @Autowired
    GMRepository gmRepository;
    @Autowired
    AddressRepository addressRepository;

   @PostMapping
    public String save(@RequestBody GMDto gmDto){
       boolean exists = gmRepository.existsByCorpNameAndDirectorName(gmDto.getCorpName(), gmDto.getDirectorName());
       boolean number = addressRepository.existsByStreetAndNumber(gmDto.getStreet(), gmDto.getNumber());
       if (exists && number) return "already exist";

       Address address =new Address(null, gmDto.getStreet(), gmDto.getNumber());
       Address savedAddres = addressRepository.save(address);

       GM gm=new GM(null, gmDto.getCorpName(), gmDto.getDirectorName(), savedAddres);
       gmRepository.save(gm);

       return "Added";

   }

   @GetMapping
    public List<GM> get(){
       List<GM> gmList = gmRepository.findAll();
       return gmList;
   }


   @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
       if (!gmRepository.existsById(id)){
           return "not found";
       }

       gmRepository.deleteById(id);
       return "deleted";
   }

   @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody GMDto gmDto){
       if (!gmRepository.existsById(id)){
           return "not found id";
       }

       Address address=new Address();
       address.setStreet(gmDto.getStreet());
       address.setNumber(gmDto.getNumber());
       Address save = addressRepository.save(address);

       GM gm = new GM();
       gm.setCorpName(gmDto.getCorpName());
       gm.setDirectorName(gmDto.getDirectorName());
       gm.setAddress(save);
       gmRepository.save(gm);
       return "editing";
   }

}
