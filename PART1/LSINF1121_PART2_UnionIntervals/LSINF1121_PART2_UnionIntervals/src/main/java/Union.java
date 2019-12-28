import java.util.ArrayList;
import java.util.Arrays;

public class Union {

    public static Interval [] union(Interval [] intervals) {
        if(intervals.length==0) return new Interval[0];
        //First I sort the intervals
        Arrays.sort(intervals);//Ils sont triés selon leur minimum.

        ArrayList<Interval> solution = new ArrayList<Interval>();

        int min = intervals[0].min;
        int max = intervals[0].max;

        for(int i = 0; i<intervals.length; i++){
            if(intervals[i].min<max){
                max = Math.max(max, intervals[i].max);
            }
            else{
                //add the new interval
                solution.add(new Interval(min,max));
                 min = intervals[i].min;
                 max = intervals[i].max;
            }

        }
        solution.add(new Interval(min,max));
        return solution.toArray(new Interval[0]);
    }

}
