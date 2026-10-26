package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateDebut;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull
    private StatutReservation statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicule", nullable = false)
    @NotNull
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    @NotNull
    private Client client;

    @OneToOne(mappedBy = "reservation")
    @JsonIgnore
    private Contrat contrat;

    @JsonIgnore
    @jakarta.validation.constraints.AssertTrue(message = "La date de fin doit être postérieure ou égale à la date de début")
    public boolean isDateRangeValid() {
        return dateDebut == null || dateFin == null || !dateFin.isBefore(dateDebut);
    }
}