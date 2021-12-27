package uz.pdp.GM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.GM.entity.GM;

public interface GMRepository extends JpaRepository<GM, Integer> {

   boolean existsByCorpNameAndDirectorName(String corpName, String directorName);



}
