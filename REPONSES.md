# Réponses — Évaluation POO : héritage, polymorphisme, transtypage

- Matricule : 2410537
- Nom et prénom :Salif CAMARA
- N (deux derniers chiffres du matricule) : 37

> Rédigez vos justifications avec vos propres mots. Elles serviront de base à l'échange oral.

## Exercice 1 — A. Affichage prédit

Écrivez ci-dessous, entre les deux lignes de trois accents graves, une ligne par ligne affichée, dans l'ordre.
Ne modifiez pas les lignes de trois accents graves.

```text
A vehicule
F moto 0
E moto 137
Moto2
vehicule
moto
C3
C2
D-vehicule
true false
true
1
Exercice 1 — A. Justifications
Ligne qui commence par « F » : S'affiche lors de l'appel au constructeur de Vehicule (super()), qui appelle la méthode demarrer(). Par polymorphisme, c'est la version redéfinie dans Moto qui s'exécute, affichant le nom de la moto et la cylindrée (qui vaut 0 car les champs de la sous-classe ne sont pas encore initialisés pendant l'appel au constructeur parent).

Ligne « C3 » : S'affiche lors de l'appel de v.comparer((Moto) v). La variable v est de type statique Vehicule et de type dynamique Moto. La surcharge de méthode avec un paramètre de type Moto est résolue dynamiquement pour le récepteur (instance de Moto -> C3), tandis que la surcharge sur le paramètre est résolue statiquement selon le type de la référence passée (Moto).

Ligne « C2 » : S'affiche lors de l'appel de v.comparer(v) où v est typé statiquement Vehicule. La méthode comparer(Vehicule v) est choisie statiquement car la référence v est de type Vehicule.

Ligne qui commence par « D- » : S'affiche via v.categorie(). Comme categorie() est une méthode static, elle est liée statiquement au type de la référence (Vehicule), ce qui donne "D-vehicule".

Pourquoi des lignes « A », « F », « E » apparaissent avant « true false » : Elles proviennent de l'instanciation de new Moto() au début du main, qui déclenche l'appel des constructeurs en cascade (parent puis enfant).

Exercice 1 — B. Transtypage
Colonne « Compile ? » : oui ou non. Colonne « Exécution » : OK, exception (en précisant laquelle), ou - si le code ne compile pas.

n°	Compile ?	Exécution	Justification
1	oui	OK	Upcasting implicite d'une Moto vers son type parent Vehicule.
2	non	-	Impossibilité d'affecter un Vehicule à une référence Moto sans transtypage explicite.
3	oui	OK	Downcasting explicite valide car la variable a référence bel et bien une Moto.
4	oui	ClassCastException	Le transtypage de b (Camion) en Moto est accepté par le compilateur (même niveau hiérarchique), mais échoue à l'exécution.
5	non	-	Incompatibilité directe détectée à la compilation entre Moto et Camion (classes sœurs).
6	oui	ClassCastException	b référence un Camion, impossible de le transtyper en String à l'exécution.
7	non	-	La classe Object ne possède pas la méthode type().
8	oui	OK	Transtypage de Object vers Vehicule redescendant l'appel vers la méthode type().