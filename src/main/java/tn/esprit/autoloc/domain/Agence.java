package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String ville;

    @Column(nullable = false, length = 200)
    @NotBlank
    @Size(max = 200)
    private String adresse;

    @Column(length = 20)
    @Size(max = 20)
    private String telephone;

    @OneToMany(mappedBy = "agence")
    @JsonIgnore
    private Set<Employe> employes = new HashSet<>();

    @OneToMany(mappedBy = "agence")
    @JsonIgnore
    private Set<Vehicule> vehicules = new HashSet<>();
}