import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {
    int [] tab;
    int nIles=0;
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        // TODO

        //je donne d'abbord un numéro à chaque position ≠
        int length = altitude.length * altitude[0].length;
        tab = new int[length];
        for(int i=0; i<length; i++){
            tab[i]=i;
        }

        for (int i=0; i<altitude.length;i++){
            for (int j=0; j<altitude[0].length;j++){

                if (altitude[i][j] > waterLevel){ //Je regarde les pas coulés
                    nIles++; //tout ce qui n'est pas coulé est une île.


                    if(i != 0 && altitude[i-1][j] > waterLevel){ //alors on check si pas coulé au dessus
                        union(j+i*altitude.length, j+(i-1)*altitude.length );
                    }
                    if (j !=0 && altitude[i][j-1] > waterLevel){//alors on check si pas coulé pile avant
                        union(j+i*altitude.length, j-1+i*altitude.length);
                    }
                }
            }
        }

    }

    public int nbIslands() {
        // TODO
        // expected time complexity O(1)
        return nIles;
    }


    public boolean onSameIsland(Point p1, Point p2) {
        // TODO
        // expected time complexity O(1)
        return find(p1.y+p1.x*altitude.length) == find(p2.y+p2.x*altitude.length) && ! p1.equals(p2);
    }


    private int find(int indice) {
        /*if(tab[indice] != indice) {
            tab[indice] = find(tab[indice]);
        }*///Ca ce serait si je fais en quick-union mais alors faut adapter un peu union aussi.
        return tab[indice];
    }

    void union(int a, int b) {
        int i = find(a);
        int j = find(b);
//A adapter si tu veux faire quick-union
        if(i==j)return;
        for (int coucou = 0; coucou<tab.length; coucou++){
            if(tab[coucou] == i){
                tab[coucou]=j;
            }
        }
        nIles--;
    }
}