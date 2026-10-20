package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}