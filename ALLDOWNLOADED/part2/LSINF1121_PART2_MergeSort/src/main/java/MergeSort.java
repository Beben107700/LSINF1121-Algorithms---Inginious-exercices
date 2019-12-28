

public class MergeSort {
    /**
     * Pre-conditions: a[lo..mid] and a[mid+1..hi] are sorted
     * Post-conditions: a[lo..hi] is sorted
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //on copie dans aux pour pouvoir réécrire dans a!
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {//on a déjà écrit tout ce qu'il y avait entre lo et mid
                a[k] = aux[j++];
            } else if (j > hi) {// on a déjà écrit tout ce qu'il y avait entre mid+1 et hi
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {//on compare pour savoir lequel mettre!
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    // Mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // TODO
        int middle = (hi-lo)/2 +lo; // ne pas oublier ce +lo!! c'est tout à fait mon genre!!
        if ( hi-lo == 0) {//longeur 0, donc arrêter la récurcivité
            return ;//ne pas mettre de valeur dans le return, notre fonction est void!
        }
        sort(a, aux, lo, middle);
        sort(a, aux, middle + 1, hi);

        //quand la récurcivité s'arrêtera, on poura merger tout ;)
        merge(a, aux, lo, middle, hi);
        return;//-> non obligatoire, car de toute facon il n'y a plus rien à faire après!
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     */
    public static void sort(Comparable[] a) {// la main est ici au final! elle alloue la mémoire pour aux,
        // TODO
        Comparable [] aux = new Comparable [a.length];
        sort(a, aux,0,a.length-1);

    }
}