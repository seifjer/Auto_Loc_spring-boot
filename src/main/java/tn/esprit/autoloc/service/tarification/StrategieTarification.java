package tn.esprit.autoloc.service.tarification;

/** Strategy (GoF) : contrat commun à toutes les politiques tarifaires. */
public interface StrategieTarification {

    double calculer(double tarifJournalier, int jours);
}
