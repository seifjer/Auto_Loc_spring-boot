package tn.esprit.autoloc.web.controller;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;
import tn.esprit.autoloc.service.tarification.TarificationService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicules")
public class VehiculeController extends AbstractCrudController<Vehicule> {

    private final VehiculeService vehiculeService;
    private final TarificationService tarificationService;

    public VehiculeController(VehiculeService service, TarificationService tarificationService) {
        super(service);
        this.vehiculeService = service;
        this.tarificationService = tarificationService;
    }

    /** Ex : GET /api/vehicules/1/tarif?type=weekend&jours=3 */
    @GetMapping("/{id}/tarif")
    public Map<String, Object> calculerTarif(@PathVariable Long id,
                                             @RequestParam(defaultValue = "standard") String type,
                                             @RequestParam int jours) {
        Vehicule vehicule = vehiculeService.findById(id);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idVehicule", id);
        body.put("type", type);
        body.put("jours", jours);
        body.put("prix", tarificationService.calculer(type, vehicule, jours));
        return body;
    }

    /** PUT /api/vehicules/1/affecter-agence/3 */
    @PutMapping("/{idVehicule}/affecter-agence/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    /** PUT /api/vehicules/1/equipements/2/affecter */
    @PutMapping("/{idVehicule}/equipements/{idEquipement}/affecter")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    /** PUT /api/vehicules/1/equipements/2/desaffecter */
    @PutMapping("/{idVehicule}/equipements/{idEquipement}/desaffecter")
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}
