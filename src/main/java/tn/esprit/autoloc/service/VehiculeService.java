package tn.esprit.autoloc.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.validation.VehiculeValidator;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

/**
 * SRP : gère uniquement les opérations métier sur les véhicules.
 * La validation est déléguée à VehiculeValidator, la journalisation à JournalisationService.
 * DIP/DI : toutes les dépendances sont injectées par le constructeur.
 */
@Service
public class VehiculeService extends AbstractCrudService<Vehicule> {

    private final VehiculeValidator validator;
    private final JournalisationService journal;
    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    public VehiculeService(VehiculeRepository repository,
                           VehiculeValidator validator,
                           JournalisationService journal,
                           AgenceRepository agenceRepository,
                           EquipementRepository equipementRepository) {
        super(repository, "Vehicule", "idVehicule");
        this.vehiculeRepository = repository;
        this.validator = validator;
        this.journal = journal;
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
    }

    /** Équivalent refactorisé de ajouter(). */
    @Override
    public Vehicule save(Vehicule vehicule) {
        validator.validerPourAjout(vehicule);
        journal.info("Ajout du véhicule {}", vehicule.getImmatriculation());
        return super.save(vehicule);
    }

    // ---------- @ManyToOne : Vehicule -> Agence ----------

    private Vehicule chargerVehicule(Long idVehicule) {
        return vehiculeRepository.findDetailsById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicule introuvable avec l'identifiant " + idVehicule));
    }

    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = chargerVehicule(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Agence introuvable avec l'identifiant " + idAgence));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = chargerVehicule(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipement introuvable avec l'identifiant " + idEquipement));
        vehicule.getEquipements().add(equipement);
        return vehiculeRepository.save(vehicule);
    }

    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = chargerVehicule(idVehicule);
        boolean retire = vehicule.getEquipements()
                .removeIf(e -> e.getIdEquipement().equals(idEquipement));
        if (!retire) {
            throw new IllegalArgumentException(
                    "L'équipement " + idEquipement + " n'est pas installé sur ce véhicule");
        }
        return vehiculeRepository.save(vehicule);
    }
}