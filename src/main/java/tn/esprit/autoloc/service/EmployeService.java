package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

@Service
public class EmployeService extends AbstractCrudService<Employe> {
    public EmployeService(EmployeRepository repository) {
        super(repository, "Employe", "idEmploye");
    }
}
