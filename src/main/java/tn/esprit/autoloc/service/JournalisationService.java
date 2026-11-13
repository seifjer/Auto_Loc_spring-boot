package tn.esprit.autoloc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * SRP : centralise la journalisation (remplace les System.out.println).
 * Le mécanisme (SLF4J) peut changer sans toucher aux services métier.
 */
@Service
public class JournalisationService {

    private static final Logger LOG = LoggerFactory.getLogger("autoloc.journal");

    public void info(String message, Object... args) {
        LOG.info(message, args);
    }
}
