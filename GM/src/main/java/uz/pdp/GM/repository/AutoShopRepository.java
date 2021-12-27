package uz.pdp.GM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.GM.entity.AutoShop;

public interface AutoShopRepository extends JpaRepository<AutoShop, Integer> {
    boolean existsByName(String name);
}
