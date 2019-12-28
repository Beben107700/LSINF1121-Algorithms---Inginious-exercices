import java.util.PriorityQueue;

public class Huffman {
    private Huffman() { }

    // Huffman trie node
    public static class Node implements Comparable<Node>{
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }

        // is the node a leaf node?
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {
        // Algorithm idea:
        /**
         * 1/ Build a list with the characters associated to frequencies (can be nodes)
         * 2/ Sort the list containing the node
         * 3/ Take the two first nodes, attach them to a node with value = sum of freqs
         * 4/ Take the next node AND the previously built and do the smae
         *
         * !! Special cases: IF the frequency is HIGHER than the previous node, you must build a new subtree
         * Use min heap to sort?? --> solves the special case.
         * Add it at the end of the algorithm.
         */

        PriorityQueue<Node> queue = new PriorityQueue<Node>(R);

        for(int i = 0; i<R; i++){
            Node node = new Node(i, freq[i], null, null);
            //The nod eI will add;
            queue.add(node);
        }
        //List is created. Now I must follow the algorithm: take two lowest & create a pair.
        while(queue.size()!= 1){ //Keep while loop going until I have only the "papa" node left
            Node first = queue.peek();
            queue.poll(); //Scrap it from list already

            Node second = queue.peek();
            queue.poll();

            Node papa = new Node(0, first.freq + second.freq, first, second);
            queue.add(papa);
        }
        return queue.peek();

    }
}
