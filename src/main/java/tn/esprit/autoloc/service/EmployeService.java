package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;

@Service
public class EmployeService extends AbstractCrudService<Employe> {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    public EmployeService(EmployeRepository repository, AgenceRepository agenceRepository) {
        super(repository, "Employe", "idEmploye");
        this.employeRepository = repository;
        this.agenceRepository = agenceRepository;
    }

    /** Un employé n'appartient qu'à UNE agence : on la retire (agence = null). */
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = findById(idEmploye);
        employe.setAgence(null);
        return employeRepository.save(employe);
    }

    /** Affecte l'employé à une agence ; refuse s'il est déjà affecté (il faut désaffecter d'abord). */
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = findById(idEmploye);
        if (employe.getAgence() != null) {
            throw new IllegalArgumentException(
                    "L'employé est déjà affecté à l'agence « " + employe.getAgence().getNom()
                            + " ». Désaffectez-le d'abord.");
        }
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new tn.esprit.autoloc.web.exception.ResourceNotFoundException(
                        "Agence introuvable avec l'identifiant " + idAgence));
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }
}