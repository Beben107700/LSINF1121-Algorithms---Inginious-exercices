import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ConnectedComponents {
    /**
     * @return the number of connected components in g
     */
    private static boolean[] marked;
    private static int count;


    public static int numberOfConnectedComponents(Graph g) {
        marked = new boolean[g.V()];
        count = 0;


        for(int v = 0; v<g.V();v++){//FOR EACH VERTEX
            if(!marked[v]){//If it is not marked,

                //AU CHOIX
                //bfs(g,v); //BFS with queue
                //dfs(g,v); //DFS recursively
                dfs2(g,v); //DFS with queue
                count++;
            }
        }
        return count;
    }
    private static void dfs(Graph G, int a){
        marked[a]=true;
        for(int b:G.adj(a)){
            if(!marked[b]){
                marked[b] = true;
                dfs(G,b);
            }
        }
    }

    private static void dfs2(Graph G, int a){
        //Queue<Integer> todo = new LinkedList<>();
        Stack<Integer> todo = new Stack<>();

        todo.push(a);

        while(!todo.isEmpty()){
            int b = todo.pop();
            for(int i:G.adj(b)){
                if(!marked[i]){
                    marked[i]=true;
                    todo.push(i);
                }
            }
        }
    }
    private static void bfs(Graph G, int a){
        Queue<Integer> todo = new LinkedList<>();
        //Stack<Integer> todo = new Stack<>();

        todo.add(a);

        while(!todo.isEmpty()){
            int b = todo.remove();
            for(int i:G.adj(b)){
                if(!marked[i]){
                    marked[i]=true;
                    todo.add(i);
                }
            }
        }
    }

}