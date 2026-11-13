# Atelier GoF & SOLID : AutoLoc

## Partie A : Problèmes de conception
1. **Responsabilités de `VehiculeService`** : (a) gestion/persistance des véhicules, (b) validation métier (tarif > 0), (c) journalisation (`System.out.println`), (d) calcul de tarif.
2. **Évolutions indépendantes** : les règles de validation changent avec le métier, le format/destination des logs avec l'exploitation (fichier, ELK...), la gestion des véhicules avec le modèle de données. Regroupées, un changement dans l'une risque de casser les autres.
3. **Principe** : SRP (une seule raison de changer), complété par OCP pour le calcul de tarif.
4. **Répartition** : `VehiculeService` (métier), `VehiculeValidator` (règles), `JournalisationService` (logs), `TarificationService` + stratégies (prix).

## Partie B
**SRP** : voir `service/VehiculeService.java` : `save()` (équivalent de `ajouter()`) = `validator.validerPourAjout()` → `journal.info()` → `super.save()`. Le `println` est remplacé par SLF4J.

**DIP**
1. `VehiculeRepository` est la couche d'accès aux données (Spring Data JPA) : une abstraction (interface) de la persistance.
2. Le service dépend d'un contrat et non d'une implémentation concrète (JDBC, JPA, mock) : il est testable (mock), et la persistance peut changer sans modifier le service.
3. Au démarrage, Spring scanne `@Repository`/`@Service`, génère un proxy d'implémentation de `VehiculeRepository` (`SimpleJpaRepository`), le crée comme bean, puis le passe au constructeur de `VehiculeService` (injection par constructeur).
4. DIP est le **principe** (dépendre d'abstractions) ; l'injection de dépendances est la **technique** qui le met en œuvre : Spring fournit l'implémentation de l'extérieur (inversion de contrôle).
Dans le projet, `AbstractCrudService` dépend de `JpaRepository<T, Long>` et le contrôleur de `CrudService<T>` : jamais de classe concrète.

## Partie C : Strategy et OCP
Package `service/tarification` : `StrategieTarification`, `TarificationStandard` (×1), `TarificationWeekend` (×0.90), `TarificationLongueDuree` (×0.80), `TarificationEntreprise` (×0.85, ajoutée sans toucher à `TarificationService`), `TarificationService` (Map<String, StrategieTarification> injectée par Spring, clé = nom du bean).
Endpoint de test : `GET /api/vehicules/{id}/tarif?type=weekend&jours=3` (types : `standard`, `weekend`, `longueDuree`, `entreprise`).

1. Strategy remplace les `if/else`/`switch` sur le type de calcul : on choisit un algorithme à l'exécution parmi une famille interchangeable.
2. Chaque formule est isolée : lisible, testable seule, modifiable sans risque pour les autres (SRP), sans conditionnelle géante.
3. OCP : ouvert à l'extension (nouvelle classe `@Component`), fermé à la modification (`TarificationService` inchangé : preuve avec `TarificationEntreprise`).
4. Si le type n'existe pas, le service lève une `IllegalArgumentException` explicite (types disponibles listés), traduite en **HTTP 400** par `GlobalExceptionHandler`. Pas de tarif par défaut silencieux, qui masquerait une erreur.

## Partie D
**Factory** : `FactureFactory` masque la création de `FacturePdf`/`FactureExcel` ; le client ne connaît que l'interface `Facture`. Méthode : `Facture creer(String type)` (ou `creerFacture(TypeFacture type)`). Avantage : création centralisée, ajout d'un format sans modifier les appelants, dépendance aux classes concrètes à un seul endroit.

**ISP** : concerne l'Interface Segregation Principle. Découper en `ConsultationVehicule` (`consulter`), `GestionVehicule` (`ajouter/modifier/supprimer`), `RapportVehicule` (`exporterRapport`), `NotificationVehicule` (`envoyerNotification`). Chaque composant ne dépend que de ce qu'il utilise.

**LSP** : une sous-classe qui lève systématiquement `UnsupportedOperationException` n'est plus substituable à `Vehicule` : le code client qui appelle la méthode héritée plante, le contrat de la classe de base est rompu. Solution : ne pas hériter de ce comportement ; revoir la hiérarchie (interface plus petite, ex. capacité séparée) ou préférer la composition.

## Synthèse
| Élément | Où |
|---|---|
| SRP | `VehiculeService` / `VehiculeValidator` / `JournalisationService` |
| DIP + DI | injection par constructeur des repositories et services |
| OCP | `TarificationService` fermé, nouvelles stratégies ajoutées |
| ISP / LSP | analysés en partie D |
| GoF Strategy | `StrategieTarification` et 4 implémentations |
| GoF Template Method | `AbstractCrudService` / `AbstractCrudController` (existant) |
| GoF Factory | analysée en partie D |
