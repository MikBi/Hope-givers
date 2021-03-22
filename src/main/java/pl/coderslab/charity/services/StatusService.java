package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Status;
import pl.coderslab.charity.repositories.StatusRepository;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    public Status single(int id) {
        return statusRepository.findTopById(id);
    }
}
