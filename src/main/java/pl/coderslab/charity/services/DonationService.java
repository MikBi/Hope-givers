package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;

import java.util.List;


@Service
public class DonationService implements SimpleServices<Donation> {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    @Override
    public Donation add(Donation donation) {
      return donationRepository.save(donation);
    }

    @Override
    public void delete(int id) {
        donationRepository.deleteById(id);
    }

    @Override
    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> all() {
        return donationRepository.findAll();
    }

    @Override
    public Donation findOne(int id) {
        return donationRepository.findTopById(id);
    }

    public Integer donationsSum() {
        return donationRepository.countDonations();
    }

    public Integer donationsQuantitySum() {
        return donationRepository.countDonationsQuantity();
    }

    public Integer last24h() {
        return donationRepository.last24hDonation();
    }

    public List<Donation> seven() {
        return donationRepository.get7Donations();
    }

    public List<Donation> allMine(int id) {
        return donationRepository.myAllDonations(id);
    }

    public List<Donation> mySeven(int id) {
        return  donationRepository.getMine7Donations(id);
    }

    public Integer myAmount(int id) {
        return donationRepository.countMyDonations(id);
    }

}
