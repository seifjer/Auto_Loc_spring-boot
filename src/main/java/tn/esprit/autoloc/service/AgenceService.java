package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

@Service
public class AgenceService extends AbstractCrudService<Agence> {
    public AgenceService(AgenceRepository repository) {
        super(repository, "Agence", "idAgence");
    }
}
