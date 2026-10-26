package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    @Size(max = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull
    private StatutVehicule statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_agence", nullable = false)
    @NotNull
    private Agence agence;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Maintenance> maintenances = new HashSet<>();

    @OneToMany(mappedBy = "vehicule")
    @JsonIgnore
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    private Set<Equipement> equipements = new HashSet<>();
}