public class Tournee {

    public static void trierFusion(Colis[] t) {
        if (t == null || t.length <= 1) {
            return;
        }
        int mid = t.length / 2;
        Colis[] gauche = new Colis[mid];
        Colis[] droite = new Colis[t.length - mid];

        System.arraycopy(t, 0, gauche, 0, mid);
        System.arraycopy(t, mid, droite, 0, t.length - mid);

        trierFusion(gauche);
        trierFusion(droite);

        fusionner(t, gauche, droite);
    }

    private static void fusionner(Colis[] t, Colis[] gauche, Colis[] droite) {
        int i = 0, j = 0, k = 0;
        while (i < gauche.length && j < droite.length) {
            if (gauche[i].compareTo(droite[j]) <= 0) {
                t[k++] = gauche[i++];
            } else {
                t[k++] = droite[j++];
            }
        }
        while (i < gauche.length) {
            t[k++] = gauche[i++];
        }
        while (j < droite.length) {
            t[k++] = droite[j++];
        }
    }

    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        if (t == null || t.length == 0 || capacite <= 0) {
            return new Colis[0];
        }

        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];

        for (int i = 1; i <= n; i++) {
            int poidsI = t[i - 1].getPoids();
            long tarifI = t[i - 1].tarif();
            for (int cap = 0; cap <= capacite; cap++) {
                if (poidsI > cap) {
                    meilleur[i][cap] = meilleur[i - 1][cap];
                } else {
                    long sans = meilleur[i - 1][cap];
                    long avec = meilleur[i - 1][cap - poidsI] + tarifI;
                    meilleur[i][cap] = Math.max(sans, avec);
                }
            }
        }

        int tempCap = capacite;
        int count = 0;
        boolean[] pris = new boolean[n];

        for (int i = n; i > 0; i--) {
            if (meilleur[i][tempCap] != meilleur[i - 1][tempCap]) {
                pris[i - 1] = true;
                count++;
                tempCap -= t[i - 1].getPoids();
            }
        }

        Colis[] resultat = new Colis[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (pris[i]) {
                resultat[idx++] = t[i];
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false),
        };

        trierFusion(lot);

        for (Colis c : lot) {
            System.out.println(c);
        }

        Colis[] optimaux = chargementOptimal(lot, Parametres.CAPACITE);
        long sommeTarifs = 0;
        for (Colis c : optimaux) {
            sommeTarifs += c.tarif();
        }

        System.out.println("Recette optimale : " + sommeTarifs + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}