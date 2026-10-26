package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicules")
public class VehiculeController extends AbstractCrudController<Vehicule> {
    public VehiculeController(VehiculeService service) {
        super(service);
    }
}
