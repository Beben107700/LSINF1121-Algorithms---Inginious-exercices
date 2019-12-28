import java.util.ArrayList;
import java.util.List;

public class DigraphImplem implements Digraph {

    private int V;
    private int E;
    private List<Integer> []edges;

    public DigraphImplem(int V) {
         this.V = V;
         this.E = 0;
        this.edges = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0;i < edges.length;i++)
        {
            edges[i] = new ArrayList<>();
        }
    }

    /**
     * The number of vertices
     */
    public int V() {

        return this.V;
    }

    /**
     * The number of edges
     */
    public int E() {
        // TODO
        return this.E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        edges[v].add(w);
        E++;
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        return edges[v];
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        Digraph reverse = new DigraphImplem(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }


}
