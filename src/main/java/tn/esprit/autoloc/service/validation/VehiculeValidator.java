package tn.esprit.autoloc.service.validation;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;

/**
 * SRP : unique responsabilité = vérifier les règles métier d'un véhicule.
 */
@Component
public class VehiculeValidator {

    public void validerPourAjout(Vehicule vehicule) {
        validerTarif(vehicule);
    }

    public void validerTarif(Vehicule vehicule) {
        BigDecimal tarif = vehicule.getTarifJournalier();
        if (tarif == null || tarif.signum() <= 0) {
            throw new IllegalArgumentException("Le tarif doit être positif");
        }
    }

    public void validerNombreJours(int nombreJours) {
        if (nombreJours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être positif");
        }
    }
}
