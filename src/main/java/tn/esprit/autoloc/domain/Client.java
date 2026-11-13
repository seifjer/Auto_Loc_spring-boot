package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Column(length = 20)
    @Size(max = 20)
    private String telephone;

    @Column(nullable = false, unique = true, length = 30)
    @NotBlank
    @Size(max = 30)
    private String numPermis;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateInscription;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Reservation> reservations = new HashSet<>();
}