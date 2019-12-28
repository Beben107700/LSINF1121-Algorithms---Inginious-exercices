//TODO import

import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo;     // edgeTo[v] = last edge on s-v path
    private final int s;

    /**
     * Computes a path between s and every other vertex in graph G
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Depth first search from v
    private void dfs(Graph G, int v) {
        marked[v]=true;
        for(int a:G.adj(v)){
            if(!marked[a]){
                marked[a]=true;
                edgeTo[a]=v;
                dfs(G,a);
            }
        }
    }

    /**
     * Is there a path between the source s and vertex v?
     * @param v the vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns a path between the source vertex s and vertex v, or
     * null if no such path
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         s and vertex v, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if(!marked[v]) return null;
        Stack<Integer> path = new Stack<>();
        int x  = v;
        while(x != s){
            path.push(x);
            x = edgeTo[x];
        }
        path.push(s);
        return path;
    }
}
