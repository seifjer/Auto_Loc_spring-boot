package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

@Service
public class VehiculeService extends AbstractCrudService<Vehicule> {
    public VehiculeService(VehiculeRepository repository) {
        super(repository, "Vehicule", "idVehicule");
    }
}
