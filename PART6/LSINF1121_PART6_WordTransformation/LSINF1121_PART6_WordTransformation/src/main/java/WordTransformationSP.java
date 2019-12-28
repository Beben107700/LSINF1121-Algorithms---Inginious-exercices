import java.util.*;

public  class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     *
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0,start)+new StringBuilder(s.substring(start,end)).reverse().toString()+s.substring(end);
    }
    private static class Entry implements Comparable{
        public String value;
        public int cost;
        public Entry(String value, int cost){
            this.value = value;
            this.cost=cost;
        }
        @Override
        public int compareTo(Object o) {
            if(o instanceof Entry){
                Entry p =(Entry) o;
                return this.cost -  p.cost;
            }
            return 0;
        }
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        HashMap<String, Integer> known = new HashMap<>();
        PriorityQueue<Entry> todo= new PriorityQueue<Entry>();

        known.put(from, 0);
        todo.add(new Entry(from,0));

        while(!todo.isEmpty()){
            Entry doing = todo.poll();
            //Get all the boyz
            //For each boy, do:
            for(int i = 0; i<to.length()-1; i++){
                for(int j = i+2; j <= (to.length());j++){ //Car on doit décaler de +1!
                    String thisboy = rotation(doing.value, i,j);
                    int newweight = doing.cost + (j-i);
                    if(!known.containsKey(thisboy) || known.get(thisboy) > newweight){
                        known.put(thisboy, newweight);
                        todo.add(new Entry(thisboy, newweight));
                    }
                }
            }
            //if(!known || is further away than now+weight){
             //   known.put(thestring,  newweight);
              //  todo.add(new Entry)
           // }
            //-------------
        }
        return known.get(to);



    }
}
