package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean valide;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false, unique = true)
    @NotNull
    private Reservation reservation;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Paiement> paiements = new HashSet<>();
}