public class Inventaire {
    public static long valeurAssuree(Object[] objets) {
        long somme = 0;
        if (objets == null) return 0;
        for (Object o : objets) {
            if (o instanceof ColisFragile) {
                somme += ((ColisFragile) o).getValeurDeclaree();
            }
        }
        return somme;
    }

    public static int[] compterParType(Object[] objets) {
        int standard = 0;
        int express = 0;
        int fragile = 0;
        int autres = 0;

        if (objets != null) {
            for (Object o : objets) {
                if (o == null) continue;
                if (o instanceof ColisFragile) {
                    fragile++;
                } else if (o instanceof ColisStandard) {
                    standard++;
                } else if (o instanceof ColisExpress) {
                    express++;
                } else {
                    autres++;
                }
            }
        }
        return new int[] { standard, express, fragile, autres };
    }
}