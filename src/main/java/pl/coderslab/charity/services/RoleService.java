package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role single(Integer id) {
        return roleRepository.findTopById(id);
    }
}
