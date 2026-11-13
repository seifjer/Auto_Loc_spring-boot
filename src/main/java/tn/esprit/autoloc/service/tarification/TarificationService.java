package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

import java.util.Map;

/**
 * OCP : ce service est fermé à la modification. Spring injecte toutes les
 * StrategieTarification (clé = nom du bean). Ajouter une politique = ajouter
 * une classe @Component, sans toucher à ce code.
 */
@Service
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;
    private final VehiculeValidator validator;

    public TarificationService(Map<String, StrategieTarification> strategies,
                               VehiculeValidator validator) {
        this.strategies = strategies;
        this.validator = validator;
    }

    public double calculer(String type, double tarifJournalier, int jours) {
        validator.validerNombreJours(jours);
        StrategieTarification strategie = strategies.get(type);
        if (strategie == null) {
            throw new IllegalArgumentException(
                    "Type de tarif inconnu : '" + type + "'. Types disponibles : " + strategies.keySet());
        }
        return strategie.calculer(tarifJournalier, jours);
    }

    public double calculer(String type, Vehicule vehicule, int jours) {
        return calculer(type, vehicule.getTarifJournalier().doubleValue(), jours);
    }
}
