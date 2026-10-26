package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

@Service
public class EquipementService extends AbstractCrudService<Equipement> {
    public EquipementService(EquipementRepository repository) {
        super(repository, "Equipement", "idEquipement");
    }
}
