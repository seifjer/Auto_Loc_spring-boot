package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ReservationRepository;

@Service
public class ReservationService extends AbstractCrudService<Reservation> {
    public ReservationService(ReservationRepository repository) {
        super(repository, "Reservation", "idReservation");
    }
}
