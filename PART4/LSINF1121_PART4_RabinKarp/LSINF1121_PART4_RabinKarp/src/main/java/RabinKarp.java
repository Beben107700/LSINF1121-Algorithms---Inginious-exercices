public class RabinKarp
{
    //TODO by student: many things are not correct here
    private String pat[]; // pattern (only needed for Las Vegas)
    private long patHash[]; // pattern hash value
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q

    public RabinKarp(String pat)
    {
        this(new String[]{pat});
    }

    public RabinKarp(String[] pat)
    {
        //TODO by student: it's obviously not correct
        this.pat= pat; // save pattern (only needed for Las Vegas)
        this.M = pat[0].length();
        Q = 4463;
        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        patHash = new long[pat.length];
        for(int i = 0; i<pat.length; i++){
            patHash[i] = hash(pat[i], M);
        }

    }


    public boolean check(int i,String txt) // Monte Carlo (See text.)
    {
        for(int j = 0; j<pat.length; j++){
            if(txt.substring(i,i+M).equals(pat[j]))return true;
        }
        return false;
    }

    private long hash(String key, int M)
    { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private boolean isMatch(long txtHash){
        for(int i = 0; i<patHash.length; i++){
            if(patHash[i]==txtHash){
                return true;
            }
        }
        return false;
    }

    public int search(String txt)
    { // Search for hash match in text.
        //TODO by student: it's obviously not correct
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (isMatch(txtHash)) return 0; // Match at beginning.
        for (int i = M; i < N; i++)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            if (isMatch(txtHash)){
                if (check(i - M + 1,txt)) return i - M + 1;} // match

        }
        return N; // no match found
    }
}