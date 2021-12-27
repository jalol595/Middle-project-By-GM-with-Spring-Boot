package uz.pdp.GM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.GM.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

   boolean existsByStreetAndNumber(String street, Integer number );

}
