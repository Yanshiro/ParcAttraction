package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParcAttraction implements IParcAttraction{
    private static final String NL = "\n";
    private static final String VIR = ",";
    private final String name;
    private final int capacity;
    private Map<Tarif,Integer>  saled;//A chaque tarif est associé le nombre d'achat de ce tarif

    public ParcAttraction(String name, int capacity) {
        Objects.requireNonNull(name);
        this.name = name;
        this.capacity = capacity;
        this.saled=new HashMap<>();
    }

    public Map<Tarif, Integer> getSaled() {
        return saled;
    }

    public int nbPlacesDispo(){
        Integer cpt=new Integer(0);
        for (Map.Entry<Tarif,Integer> entry : saled.entrySet()){
            cpt+=entry.getValue();
                    }
        return capacity-cpt;
    }
    public void init(){//même role que la méthode reinitialiser
        for(Tarif t : Tarif.values()){
            saled.put(t,0);
        }
    }

    public void vendreBillets(int nb, Tarif tarif){
        if (this.nbPlacesDispo()<nb){
            System.out.println("Plus de place restante");
        }
        else {
            int a = saled.get(tarif);
            saled.replace(tarif,a+nb);
        }
    }

    public double chiffreAffaires(){
        double ca;
        ca = saled.get(Tarif.INDJRILL) * Tarif.INDJRILL.getValeur() +
                saled.get(Tarif.INDJRILL5) * Tarif.INDJRILL5.getValeur()
                + saled.get(Tarif.REDILL) * Tarif.REDILL.getValeur()
                + saled.get(Tarif.REDILL5) * Tarif.REDILL5.getValeur();
        return ca;
    }

    public double tauxRemplissage(){
        int cpt=0;
        for(Map.Entry<Tarif,Integer> entry : saled.entrySet()){
            cpt=cpt+entry.getValue();
        }

        return (double)cpt/capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(VIR).append(NL)
                .append("Capacité d'accueil : ").append(capacity).append(VIR).append(NL)
                .append("Tarif normal illimité +  : ").append(Tarif.INDJRILL5.getValeur()).append(" euros").append(VIR).append(NL)
                .append("Tarif normal illimité  5+  : ").append(Tarif.INDJRILL5.getValeur()).append(" euros").append(VIR).append(NL)
                .append(saled.get(Tarif.INDJRILL)).append(" billets vendus au tarif illimité").append(VIR).append(NL)
                .append(saled.get(Tarif.INDJRILL5)).append(" billets vendus au tarif illimité 5").append(VIR).append(NL)
                .append(saled.get(Tarif.REDILL)).append(" billets vendus au tarif réduit").append(VIR).append(NL)
                .append(saled.get(Tarif.REDILL5)).append(" billets vendus au tarif réduit 5").append(VIR).append(NL);
        return sb.toString();
    }

    public static void main(String[] args) {//on a laissé remonter les nullpointer exception pour les gérer dans le main
        ParcAttraction p =new ParcAttraction("Asterix",40);
        p.init();
        try {
            p.vendreBillets(5,Tarif.REDILL5);
            p.vendreBillets(15,Tarif.REDILL);
            p.vendreBillets(2,Tarif.INDJRILL);
            System.out.println(p.nbPlacesDispo());
            System.out.println("Taux de remplissage : "+ p.tauxRemplissage());
            System.out.println("Chiffre d'affaire : "+ p.chiffreAffaires() +"euros");

        }
        catch (NullPointerException e) {
            System.out.println("pas de cle");
        }
    }

}
