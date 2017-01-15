package HackerCup2017;

import java.io.*;
import java.util.*;
import java.math.*;

public class PieProgress {
    public static void main(String args[]) {
        try {
            InputReader in = new InputReader(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            int tc = in.readInt();
            for (int t = 0; t < tc; t++) {
                int n = in.readInt();
                int m = in.readInt();
                int c[][] = new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        c[i][j] = in.readInt();
                    }
                }
                Queue<Integer> pq[] = new PriorityQueue[n];
                for (int i = 0; i < n; i++) {
                    pq[i] = new PriorityQueue<>();
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        pq[i].add(c[i][j]);
                    }
                }
                int bought[] = new int[n];
                int sum[] = new int[n];
                int total = 0;
                while(total != n) {
                    Vector<PriceIndex> p = new Vector<>();
                    for (int i = 0; i <= total; i++) {
                        if (pq[i].size() == 0) {
                            continue;
                        }
                        long value = sum[i] + pq[i].peek() + ((bought[i] + 1) * (bought[i] + 1));
                        for (int j = 0; j < n; j++) {
                            if (j == i) {
                                continue;
                            }
                            value += (sum[j]) + (bought[j] * bought[j]);
                        }
                        p.add(new PriceIndex(value, i));
                    }
                    Collections.sort(p);
                    PriceIndex sel = p.get(0);
                    bought[sel.ind]++;
                    sum[sel.ind] += pq[sel.ind].peek();
                    pq[sel.ind].poll();
                    total++;
                }
                long ans = 0l;
                for (int i = 0; i < n; i++) {
                    ans += (sum[i]) + (bought[i] * bought[i]);
                }
                out.write("Case #"+(t+1)+": "+Long.toString(ans));
                out.newLine();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class PriceIndex implements Comparable<PriceIndex>{
    long p; int ind;
    public PriceIndex(long p, int ind) {
        this.p = p;
        this.ind = ind;
    }
    public int compareTo(PriceIndex that) {
        if (this.p < that.p) {
            return -1;
        }
        else
            return 1;
    }
}
