import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {
    private int[] precedent;
    private int[] disto;
    private boolean[] marked;

    private int width;
    private int height;
    private int size;

    private int[][] altitude;
    private int waterLevel;
    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        height = altitude.length;
        width = altitude[0].length;
        size = height*width;

        precedent = new int[size];
        disto = new int[size];
        for(int i = 0; i<size; i++){
            disto[i]=-1;
        }
        marked=new boolean[size];

        this.altitude=altitude;
        this.waterLevel=waterLevel;

    }


//X*WIDTH + y
    private void bfs(Point p1){

        Queue<Integer> todo = new LinkedList<>();
        todo.add(p1.x+p1.y * width);
        while(!todo.isEmpty()){
            int a = todo.remove();
            marked[a]=true;
            //For each adjacent b  of a in todo, do:
            int x = a%width;
            int y = a/width;
            for(int dx = -1; dx<=1;dx++){
                for(int dy=-1; dy<=1; dy++){

                    // x pas plus petit que zero, y idem, x pas plus grand que width, y pas plus gd que length
                    // point pas sous l'eau
                    if(x+dx >=0 && x+dx <width && y+dy>=0 && y+dy < height && altitude[x+dx][y+dy]> waterLevel && Math.abs(dx+dy) == 1){
                        int b = (x+dx) + (y+dy)*width;
                        if(!marked[b]){
                            marked[b]=true;
                            todo.add(b);
                            disto[b]=disto[a]+1;
                            precedent[b]=a;
                        }

                    }
                }
            }
        }
    }

    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    public List<Point> shortestPath(Point a, Point b) {
        Point p1 = b;
        Point p2 = a;
        //If p1 or p2 altitude is under water return null;
        ArrayList<Point> toreturn = new ArrayList<Point>();
        if(altitude[p1.x][p1.y] <= waterLevel || altitude[p2.x][p2.y] <= waterLevel){
            return null;
        }
        bfs(p1); //Doing bfs
        if(!marked[p2.x + p2.y*width]){ //If it is not marked
            return toreturn;
        }
        //If I'm here it means there is a path!

        for(int i = p2.x+p2.y*width; i != p1.x+p1.y*width; i=precedent[i]){
            toreturn.add(new Point(i%width,i/width));
        }
        toreturn.add(p1);
        return toreturn;



    }

}