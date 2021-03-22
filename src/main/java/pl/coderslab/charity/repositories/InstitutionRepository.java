package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution,Integer> {

    Institution findTopById (int id);

    @Query("select count(i) from Institution i")
    Integer countAllInstitutions();

}
