package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

/**
 * SRP : gère uniquement les opérations métier sur les véhicules.
 * La validation est déléguée à VehiculeValidator, la journalisation à JournalisationService.
 * DIP/DI : toutes les dépendances sont injectées par le constructeur.
 */
@Service
public class VehiculeService extends AbstractCrudService<Vehicule> {

    private final VehiculeValidator validator;
    private final JournalisationService journal;

    public VehiculeService(VehiculeRepository repository,
                           VehiculeValidator validator,
                           JournalisationService journal) {
        super(repository, "Vehicule", "idVehicule");
        this.validator = validator;
        this.journal = journal;
    }

    /** Équivalent refactorisé de ajouter(). */
    @Override
    public Vehicule save(Vehicule vehicule) {
        validator.validerPourAjout(vehicule);
        journal.info("Ajout du véhicule {}", vehicule.getImmatriculation());
        return super.save(vehicule);
    }
}
