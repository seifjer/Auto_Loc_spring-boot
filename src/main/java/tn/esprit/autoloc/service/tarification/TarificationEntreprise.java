package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/** Tarif entreprise : réduction de 15 % (ajoutée sans modifier TarificationService - OCP). */
@Component("entreprise")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.85;
    }
}
