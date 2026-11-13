package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

@Service
public class ContratService extends AbstractCrudService<Contrat> {
    public ContratService(ContratRepository repository) {
        super(repository, "Contrat", "idContrat");
    }
}
