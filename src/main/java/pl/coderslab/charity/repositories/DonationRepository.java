package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Donation;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Integer> {

    Donation findTopById (int id);

    @Query(value = "select count(d.id) from Donation d")
    Integer countDonations();

    @Query(value = "select count(d.id) from Donation d where d.user.id = ?1")
    Integer countMyDonations(int id);

    @Query(value = "select sum(d.quantity) from Donation d")
    Integer countDonationsQuantity();

    @Query(value = "select count(id) from `charity-donation`.donation where date > now() - INTERVAL 1 DAY; ", nativeQuery = true)
    Integer last24hDonation();

    @Query(value = "SELECT * FROM `charity-donation`.donation ORDER BY id DESC LIMIT 7", nativeQuery = true)
    List<Donation> get7Donations();

    @Query(value = "SELECT * FROM `charity-donation`.donation where user_id = ? ORDER BY id DESC LIMIT 7", nativeQuery = true)
    List<Donation> getMine7Donations(int id);

    @Query(value = "select d from Donation d where d.id = ?1")
    List<Donation> myAllDonations(int id);
}
