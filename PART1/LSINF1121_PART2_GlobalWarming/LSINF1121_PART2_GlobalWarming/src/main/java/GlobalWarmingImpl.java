

import java.util.Arrays;

public class GlobalWarmingImpl extends GlobalWarming {

    int[] oneline;
    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        oneline = new int[altitude.length * altitude.length];

        for(int i=0; i<altitude.length; i++){
            for(int j = 0; j<altitude[i].length;j++){
                oneline[i*(altitude.length)+j] = altitude[i][j];
            }
        }
        Arrays.sort(oneline);

    }


    public int nbSafePoints(int waterLevel) {

        if(waterLevel < oneline[0]){
            return oneline.length;
        }
        if(waterLevel>oneline[oneline.length -1]){
            return 0;
        }
        else{
            return oneline.length - research(0,oneline.length-1,waterLevel);
        }
    }
    public int research(int low, int high, int waterLevel){
        if(low<high){
            int mid = low + (int) (high-low )/2;
            if(oneline[mid] <= waterLevel && oneline[mid+1]>waterLevel){
                return mid+1;
            }
            if(oneline[mid]> waterLevel){
                return research(low,mid,waterLevel);
            }
            if(oneline[mid] <= waterLevel) {
                return research(mid, high,waterLevel);
            }
        }
        return low;
    }

}