import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    private static int width;
    private static boolean[] marked;
    private static int[] from;

    public static Iterable<Integer> shortestPath(int [][] maze,  int x2, int y2, int x1, int y1) {
        if(maze[x1][y1] == 1 || maze[x2][y2] == 1) {
            return new LinkedList<>();
        }

        Queue<Integer> todo = new LinkedList<Integer>();
        width = maze.length;
        marked = new boolean[maze[0].length * width];
        from = new int[maze[0].length * width];
        for(int i: from){
            from[i] = -1;
        }

        int source = x1+y1*width;
        int destination = x2+y2*width;

        todo.add(source);
        int zozo = 0;
        while(!todo.isEmpty()){
            zozo++;
            int current = todo.remove();
            marked[current]=true;
            for(int dx = -1; dx <=1;dx++){
                for(int dy =-1; dy<=1; dy++){
                    int x = current%width +dx;
                    int y = (int) current/width +dy;

                    int dxdy = x+y*width;

                    if(x>=0 && x<width && y>=0 && y<maze.length && Math.abs(dx+dy) == 1 && maze[x][y] == 0 && !marked[dxdy]) {
                        marked[dxdy] = true;
                        from[dxdy] = current;
                        todo.add(dxdy);

                    }
                }
            }
        }
        //DONE finding the path.



        if(marked[destination]){
            LinkedList<Integer> toreturn = new LinkedList<>();
            for(int i = destination; i!=source; i=from[i]){
                toreturn.add(i);
            }
            toreturn.add(source);

            return toreturn;
        }

        return new LinkedList<>();
    }

    public static int ind(int x,int y, int lg) {return x*lg + y;}

    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }
}
