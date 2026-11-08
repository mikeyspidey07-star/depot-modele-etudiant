/**
 * Paramètres personnels de l'évaluation.
 * 1) Remplacez MATRICULE par votre matricule complet.
 * 2) Remplacez N par les DEUX DERNIERS CHIFFRES de votre matricule (ex. 2410537 -> 37).
 * Ne modifiez rien d'autre dans ce fichier : les valeurs ci-dessous sont calculées à partir de N.
 */
public final class Parametres {
    public static final String MATRICULE = "2410537";
    public static final int N = 37; 

    public static final long TARIF_KG = 400 + 10 * (N % 10); 
    public static final long SURCHARGE_HORS_DAKAR = 1500; 
    public static final int CAPACITE = 15 + N % 7;
    private Parametres() {}
}
