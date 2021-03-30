package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService implements SimpleServices<Institution> {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Institution add(Institution institution) {
      return institutionRepository.save(institution);
    }

    @Override
    public void delete(int id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public List<Institution> all() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution findOne(int id) {
        return institutionRepository.findTopById(id);
    }

    public Integer sum() {
        return institutionRepository.countAllInstitutions();
    }
}
