package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

@Service
public class ClientService extends AbstractCrudService<Client> {
    public ClientService(ClientRepository repository) {
        super(repository, "Client", "idClient");
    }
}
