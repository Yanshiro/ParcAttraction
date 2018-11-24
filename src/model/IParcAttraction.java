package model;

public interface IParcAttraction {
    double chiffreAffaires();
    void vendreBillets(int nb, Tarif tarif);
    int nbPlacesDispo();

}
