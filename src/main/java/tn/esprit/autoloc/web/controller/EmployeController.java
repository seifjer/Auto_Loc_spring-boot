package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employes")
public class EmployeController extends AbstractCrudController<Employe> {

    private final EmployeService employeService;

    public EmployeController(EmployeService service) {
        super(service);
        this.employeService = service;
    }

    /** PUT /api/employes/1/desaffecter */
    @PutMapping("/{idEmploye}/desaffecter")
    public Employe desaffecterEmploye(@PathVariable Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }

    /** PUT /api/employes/1/affecter/2 */
    @PutMapping("/{idEmploye}/affecter/{idAgence}")
    public Employe affecterEmploye(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}