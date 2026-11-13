package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.ContratService;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats")
public class ContratController extends AbstractCrudController<Contrat> {
    public ContratController(ContratService service) {
        super(service);
    }
}
