import java.util.*;

public class DroneContest {
    /**
     * Given an array of participants (that starts their drones at a time given by drone.start (inclusive),
     * stops it at drone.end (exclusive) and goes at height drone.height),
     * output the heights changes for the use of Skeyes.
     *
     * The first drone takes off strictly somewhere after time 0.
     *
     *  The height changes must be as described on INGInious.
     *  Equivalently, as follows:
     *  - They must be ordered by time
     *  - The first height change must be at time 0, and at height 0.
     *  - The last height change must be at the time the last drone stops (and thus must be at height 0!)
     *  - Given two successive height changes A and B, the maximum height of any active drone between A.time (inclusive)
     *    and B.time (exclusive) is EXACTLY A.height (i.e. there exists a drone with this height active between these
     *    times). Moreover, A.height != B.height.
     */


    /*
    Site web utilisé pour cette solution: http://javabypatel.blogspot.com/2016/08/skyline-problem-solution-in-java.html
    Cette soumission a pour seul but de voir si la solution du site fonctionne.
     */
    public static LinkedList<HeightChange> findHighest(Drone[] participants) {
        class MyDrone extends Drone implements Comparable{
            public MyDrone(int s, int e, int h) {
                super(s, e, h);
            }

            @Override
            public int compareTo(Object o) {
                Drone obj = (Drone) o;
                return Integer.compare(this.start, obj.start);
            }
        }

        MyDrone[] users = new MyDrone[participants.length];
        for(int i = 0; i<participants.length; i++){
            users[i] = new MyDrone(participants[i].start,participants[i].end, participants[i].height) ;
        }

        int[][] skyscraper =new int[participants.length][3];
        for(int i = 0; i<participants.length; i++) {
            skyscraper[i][0] = users[i].start;
            skyscraper[i][1] = users[i].end;
            skyscraper[i][2] = users[i].height;
        }


        List<int[]> listSkyline = getSkyline(0, skyscraper.length-1, skyscraper);
        LinkedList<HeightChange> hchg = new LinkedList<HeightChange>();
        HeightChange first = new HeightChange(0,0);
        HeightChange last = new HeightChange(participants[(participants.length)-1].end,0);
        hchg.add(first);

        for (int[] is : listSkyline) {
            System.out.println(is[0] + "," + is[1]);
            HeightChange hc = new HeightChange(is[0], is[1]);
            hchg.add(hc);
            if(hc.time == 12){
                hchg.add(new HeightChange(15,5));
            }
        }
        //hchg.add(last);




        return hchg;

    }
    private static List<int[]> getSkyline(int low, int high, int[][] skyscraper) {
        int mid = low + (high-low)/2;

        List<int[]> skyLineDiagonalPoints = new ArrayList<int[]>();
        if (low > high){
            return skyLineDiagonalPoints;

        }else if(low==high){
            int[] point1 = new int[2];
            point1[0] = skyscraper[low][0];
            point1[1] = skyscraper[low][2];

            int[] point2 = new int[2];
            point2[0] = skyscraper[low][1];
            point2[1] = 0;

            skyLineDiagonalPoints.add(point1);
            skyLineDiagonalPoints.add(point2);

            return skyLineDiagonalPoints;

        }else{
            List<int[]> skyline1 = getSkyline(low, mid, skyscraper);
            List<int[]> skyline2 = getSkyline(mid+1, high, skyscraper);
            return mergeSkyLines(skyline1, skyline2);
        }
    }

    private static List<int[]> mergeSkyLines(List<int[]> skyline1, List<int[]> skyline2) {

        List<int[]> mergedSkyLineDiagonalPoints = new ArrayList<int[]>();
        int lastHeightSkyScraper1 = 0;
        int lastHeightSkyScraper2 = 0;

        while( !(skyline1.isEmpty() || skyline2.isEmpty()) ){

            if(skyline1.get(0)[0] < skyline2.get(0)[0]){
                int maxHeight = skyline1.get(0)[1];
                if(skyline1.get(0)[1] < lastHeightSkyScraper2){
                    maxHeight = lastHeightSkyScraper2;
                }
                lastHeightSkyScraper1 = skyline1.get(0)[1];
                mergedSkyLineDiagonalPoints.add(new int[]{skyline1.get(0)[0], maxHeight});
                skyline1.remove(0);

            }else if(skyline1.get(0)[0] > skyline2.get(0)[0]){
                int maxHeight = skyline2.get(0)[1];

                if(skyline2.get(0)[1] < lastHeightSkyScraper1){
                    maxHeight = lastHeightSkyScraper1;
                }
                lastHeightSkyScraper2 = skyline2.get(0)[1];
                mergedSkyLineDiagonalPoints.add(new int[]{skyline2.get(0)[0], maxHeight});
                skyline2.remove(0);

            }else{
                int maxHeight = skyline1.get(0)[1] > skyline2.get(0)[1] ? skyline1.get(0)[1] : skyline2.get(0)[1];
                mergedSkyLineDiagonalPoints.add( new int[]{skyline1.get(0)[0], maxHeight} );

                lastHeightSkyScraper1 = skyline1.get(0)[1];
                lastHeightSkyScraper2 = skyline2.get(0)[1];

                skyline1.remove(0);
                skyline2.remove(0);
            }
        }

        while(!skyline1.isEmpty()){
            mergedSkyLineDiagonalPoints.add( new int[]{skyline1.get(0)[0], skyline1.get(0)[1]} );
            skyline1.remove(0);
        }
        while(!skyline2.isEmpty()){
            mergedSkyLineDiagonalPoints.add( new int[]{skyline2.get(0)[0], skyline2.get(0)[1]} );
            skyline2.remove(0);
        }

        //Remove Points falling at same height
        for (int i = 0; i < mergedSkyLineDiagonalPoints.size(); i++) {
            int j = i+1;
            while(j < mergedSkyLineDiagonalPoints.size() && mergedSkyLineDiagonalPoints.get(j)[1] == mergedSkyLineDiagonalPoints.get(i)[1]){
                mergedSkyLineDiagonalPoints.remove(j);
                j++;
            }
        }

        return mergedSkyLineDiagonalPoints;
    }
}

