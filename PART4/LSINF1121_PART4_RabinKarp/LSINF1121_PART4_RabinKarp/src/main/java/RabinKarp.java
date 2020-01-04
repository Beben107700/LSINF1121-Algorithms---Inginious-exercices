// ShaniaTwain est passée par là
public class RabinKarp
{
    private String[] pat; // pattern (only needed for Las Vegas)
    private long[] patHash; // pattern hash value
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
        this.pat = pat; // save pattern (only needed for Las Vegas)
        this.patHash = new long[this.pat.length];
        this.M = pat[0].length();
        Q = 4463;
        RM = 1;
        for (int i = 1; i <= M-1; i++) { // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        }
        for (int i = 0; i < this.pat.length; i++) {
            patHash[i] = hash(pat[i], M);
        }
    }

    public boolean check(int i,String txt, String pattern) // Monte Carlo (See text.)
    {
        if (txt.substring(i, i + M).equals(pattern)) {
            return true;
        }
        return false;
    }

    public String hashMatchPattern(long hash){
        for (int j=0; j<this.pat.length; j++) {
            if (hash == this.patHash[j]) {
                return this.pat[j];
            }
        }
        return null;
    }

    private long hash(String key, int M)
    { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }


    public int search(String txt)
    { // Search for hash match in text.
        String patternCollector;
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (N < M) {return N;}
        patternCollector = hashMatchPattern(txtHash);
        if (patternCollector != null){
            if (check(0, txt, patternCollector)){
                return 0;
            }
        }

        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            patternCollector = hashMatchPattern(txtHash);
            if (patternCollector != null){
                if (check(i - M + 1, txt, patternCollector)){
                    return i - M + 1;
                }
            }
        }
        return N; // no match found
    }
}
