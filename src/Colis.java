public abstract class Colis implements Comparable<Colis> {
    private String code;
    private int poids;
    private boolean horsDakar;

    public Colis(String code, int poids, boolean horsDakar) {
        if (poids <= 0) {
            throw new IllegalArgumentException("Le poids doit être strictement supérieur à 0.");
        }
        this.code = code;
        this.poids = poids;
        this.horsDakar = horsDakar;
    }

    public String getCode() {
        return code;
    }

    public int getPoids() {
        return poids;
    }

    public boolean isHorsDakar() {
        return horsDakar;
    }

    long tarifBase() {
        return (long) poids * Parametres.TARIF_KG + (horsDakar ? Parametres.SURCHARGE_HORS_DAKAR : 0);
    }

    public abstract long tarif();
    public abstract String sigle();
    public abstract int priorite();

    @Override
    public String toString() {
        return "[" + sigle() + "] " + code + " " + poids + "kg " + (horsDakar ? "hors-Dakar" : "Dakar") + " " + tarif() + " FCFA";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Colis colis = (Colis) o;
        return code.equals(colis.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode() + getClass().hashCode();
    }

    @Override
    public int compareTo(Colis c) {
        int compPrio = Integer.compare(c.priorite(), this.priorite());
        if (compPrio != 0) return compPrio;

        int compTarif = Long.compare(c.tarif(), this.tarif());
        if (compTarif != 0) return compTarif;

        return this.code.compareTo(c.code);
    }
}