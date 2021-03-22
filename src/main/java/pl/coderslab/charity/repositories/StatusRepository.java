package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Status;


@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {

    Status findTopById(int id);

}
