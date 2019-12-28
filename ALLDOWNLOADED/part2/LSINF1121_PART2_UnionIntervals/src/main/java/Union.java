import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Union {

    public static Interval [] union(Interval [] intervals) {
        // TODO
        if (intervals.length==0)
            return intervals;
        Arrays.sort(intervals);
        int min = intervals[0].min;
        int max = intervals[0].max;
        ArrayList<Interval> ret = new ArrayList<Interval>();
        for(int i = 1; i<intervals.length; i++){
            if(intervals[i].min > max){// ça veut dire que l'intervale précédente doit être fermée!
                ret.add(new Interval(min, max));
                min = intervals[i].min;// on mets min et max à l'intervalle courante
                max = intervals[i].max;
            }
            else if(intervals[i].max>max){// on doit agrandir l'intervale!
                max=intervals[i].max;
            }
        }
        ret.add(new Interval(min, max));// il faut pas oublier d'ajouter la dernière!
        return ret.toArray(new Interval[0]);
    }
}
