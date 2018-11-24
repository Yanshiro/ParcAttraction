package model;

public enum Tarif {
    INDJRILL(38.5),
    INDJRILL5(28.5),
    REDILL(26.95),
    REDILL5(19.95);

    private Tarif(double valeur){
        this.valeur = valeur;
    }

    public double getValeur() {
        return this.valeur;
    }

    private final double valeur;
}
