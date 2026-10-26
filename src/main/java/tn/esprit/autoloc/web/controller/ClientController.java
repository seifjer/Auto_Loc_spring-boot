package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.ClientService;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients")
public class ClientController extends AbstractCrudController<Client> {
    public ClientController(ClientService service) {
        super(service);
    }
}
