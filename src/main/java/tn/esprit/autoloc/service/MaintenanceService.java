package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;

@Service
public class MaintenanceService extends AbstractCrudService<Maintenance> {
    public MaintenanceService(MaintenanceRepository repository) {
        super(repository, "Maintenance", "idMaintenance");
    }
}
