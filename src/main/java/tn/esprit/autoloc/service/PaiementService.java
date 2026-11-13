package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;

@Service
public class PaiementService extends AbstractCrudService<Paiement> {
    public PaiementService(PaiementRepository repository) {
        super(repository, "Paiement", "idPaiement");
    }
}
