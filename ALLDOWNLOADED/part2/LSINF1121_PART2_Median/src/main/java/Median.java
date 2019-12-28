
public class Median {

    /*public static int median(Vector a, int lo, int hi) {
        // TODO
        int milieu=(hi-lo)/2;
        int pluspetit;
        int pivot;
        int j;
        while (hi>lo){
            pluspetit=lo-1;//ne pas oublier le -1!! après on  fait tout le temps ++ donc si on l'a oublié, on passe le 1er
            pivot=hi;
            j=lo;
            while(j < hi){//parcourt le tab
                if (a.get(j)<a.get(pivot)){//si plus petit, on le mets à la suite des plus petits
                    pluspetit++;
                    if (j!=pluspetit) {
                        a.swap(j, pluspetit);
                    }
                }
                j++;
            }
            //on doit remettre le pivot au milieu!
            if (pivot != pluspetit){
                a.swap(pivot, pluspetit+1);
            }

            if (pivot == milieu){
                return a.get(pivot);
            }
            else if (pivot > milieu){
                hi=pivot-1;//on continue avec la partie à droite du pivot! -1 car on sait qu'il n'est pas égal
            }
            else{
                lo= pivot +1; // on continue avec la partie à gauche du pivot!
            }
        }
        return -1;// je mets ça juste car il me demande de retourner quelque chose ;)
    }*/

    //solutions trouvés dans l'autre projet sur inginious!!! Je sais toujours pas où est mon erreur dans celui d'au dessus!
    public static int median(Vector a, int lo, int hi) {

        int i = partition(a,lo,hi);
        if (i == a.size()/2) return a.get(i);
        else if (i < a.size()/2) {
            return median(a,i+1,hi);
        } else {
            return median(a,lo,i-1);
        }

    }

    public static int partition (Vector a,int lo, int hi){
        int i = lo, j = hi + 1;
        int v = a.get(lo);
        while (true) {
            while (a.get(++i) < v) if (i == hi) break;
            while (v < a.get(--j)) if (j == lo) break;
            if (i >= j) break;
            a.swap(i, j);
        }
        a.swap(lo, j);
        return j;
    }
}