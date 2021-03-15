package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Integer> {

    Donation findTopById (int id);

    @Query(value = "select count(d.id) from Donation d")
    Integer countDonations();

    @Query(value = "select sum(d.quantity) from Donation d")
    Integer countDonationsQuantity();
}
