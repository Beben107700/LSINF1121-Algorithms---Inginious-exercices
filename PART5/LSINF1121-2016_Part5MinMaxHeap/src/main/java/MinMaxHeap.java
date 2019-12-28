import java.util.Arrays;

public class MinMaxHeap<Key extends Comparable<Key>> {

    // ---------------------------------------------------------------------------------------
    // Instance variables
    // ---------------------------------------------------------------------------------------

    // You are not allowed to add/delete variables, nor modifying their names or visibility.
    public Key[] pq;          // contains the elements starting at position 1
    public int N = 0;         // number of elements in the heap
    public int height = 0;    // should help you to know if you are at a level min or max

    // ---------------------------------------------------------------------------------------------------
    // Functions that you should implement. This is the only part of this class that you should modify ;-)
    // ---------------------------------------------------------------------------------------------------

    /**
     * @pre size() >= 1
     */
    public Key min() {
        return pq[1];
    }

    /**
     * @pre size() >= 1
     */
    public Key max() {
        return pq[2];
    }

    private int getParent(int k){
        return k/2;
    }
    private boolean isMax(int k){
        if(k%2 != 0){
            return true;
        }
        return false;
    }
    /**
     * @pre 1 <= k <= size()
     */
    private void swim(int k) {
        //compare to parent
        Key add = pq[k];
        Key parent = pq[getParent(k)];

        int comp = add.compareTo(parent);
        if(isMax(getParent(k))){
            //If my parent is a MAX layer,
            if(comp > 0){//If I'm greater than my parent
                //Then I should be a MAX.
                exch(k,getParent(k));
            }


        }
        else{//If parent is a MIN layer


        }
    }

    // ---------------------------------------------------------------------------------------
    // Constructor and helpers. You should not modify this. However, using them may be useful.
    // ---------------------------------------------------------------------------------------

    public MinMaxHeap(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * @return pq[i] < pq[j]
     */
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Exchanges positions i and j in pq
     */
    private void exch(int i, int j) {
        Key e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    /**
     * @return true if the heap is empty, false else
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return the size of the heap
     */
    public int size() {
        return N;
    }

    /**
     * @param v value to insert in the heap. v != null.
     */
    public void insert(Key v) {
        pq[++N] = v;
        if (N >= (1 << height)) height++;
        swim(N);
    }

    @Override
    public String toString() {
        return Arrays.toString(pq);
    }
}