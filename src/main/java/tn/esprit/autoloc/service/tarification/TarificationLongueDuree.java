package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/** Tarif longue durée : réduction de 20 %. */
@Component("longueDuree")
public class TarificationLongueDuree implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.80;
    }
}
