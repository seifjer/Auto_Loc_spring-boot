package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    @Query("select v from Vehicule v " +
            "left join fetch v.agence " +
            "left join fetch v.equipements " +
            "where v.idVehicule = :id")
    Optional<Vehicule> findDetailsById(@Param("id") Long id);
}