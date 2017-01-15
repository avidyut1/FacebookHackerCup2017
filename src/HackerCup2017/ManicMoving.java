package HackerCup2017;
import java.io.*;
import java.util.*;
import java.math.*;

public class ManicMoving {
    public static void main(String args[]) {
        try {
            InputReader in = new InputReader(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            int tc = in.readInt();
            for (int t = 0; t < tc; t++) {
                int n = in.readInt();
                int m = in.readInt();
                int k = in.readInt();
                Vector<Edge> g[] = new Vector[n];
                for (int i = 0; i < n; i++) {
                    g[i] = new Vector<Edge>();
                }
                final long inf = (long) 1e9 + 1;
                long shortestpath[][] = new long[n][n];
                for (int i = 0; i < n; i++) {
                    Arrays.fill(shortestpath[i], inf);
                }
                long graph[][] = new long[n][n];
                for (int i = 0; i < n; i++) {
                    Arrays.fill(graph[i], inf);
                }
                for (int i = 0; i < m; i++) {
                    int a = in.readInt() - 1;
                    int b = in.readInt() - 1;
                    int val = in.readInt();
                    graph[a][b] = Math.min(graph[a][b], val);
                    graph[b][a] = graph[a][b];
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == j) {
                            continue;
                        }
                        if (graph[i][j] != inf) {
                            g[i].add(new Edge(i, j, graph[i][j]));
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (Edge ed : g[i]) {
                        shortestpath[i][ed.to] = Math.min(shortestpath[i][ed.to], ed.g);
                    }
                }
                for (int i = 0; i < n; i++) {
                    shortestpath[i][i] = 0;
                }
                for (int ki = 0; ki < n; ki++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            shortestpath[i][j] = Math.min(shortestpath[i][j], shortestpath[i][ki] + shortestpath[ki][j]);
                        }
                    }
                }
                int from[] = new int[k];
                int to[] = new int[k];
                for (int i = 0; i < k; i++) {
                    from[i] = in.readInt() - 1;
                    to[i] = in.readInt() - 1;
                }
                long res = shortestpath[0][from[0]];
                long dp[][] = new long[k][2];
                for (int i = 0; i < k; i++) {
                    Arrays.fill(dp[i], inf);
                }
                dp[0][0] = res + shortestpath[from[0]][to[0]];
                if (k >= 2) {
                    dp[0][1] = res + shortestpath[from[0]][from[1]] + shortestpath[from[1]][to[0]];
                }
                for (int i = 1; i < k; i++) {
                    dp[i][0] = Math.min(dp[i - 1][0] + shortestpath[to[i - 1]][from[i]] + shortestpath[from[i]][to[i]], dp[i - 1][1] + shortestpath[to[i - 1]][to[i]]);
                    if (i + 1 < k) {
                        long minload = shortestpath[to[i - 1]][from[i + 1]] + shortestpath[from[i + 1]][to[i]];
                        dp[i][1] = dp[i - 1][1] + minload;
                        minload = shortestpath[from[i]][from[i + 1]] + shortestpath[from[i + 1]][to[i]];
                        dp[i][1] = Math.min(dp[i][1], minload + dp[i - 1][0] + shortestpath[to[i - 1]][from[i]]);
                    } else {
                        dp[i][1] = inf;
                    }
                }
                if (Math.min(dp[k - 1][0], dp[k - 1][1]) < inf)
                    out.write("Case #" + (t + 1) + ": " + Long.toString(Math.min(dp[k - 1][0], dp[k - 1][1])));
                else
                    out.write("Case #" + (t + 1) + ": " + Integer.toString(-1));
                out.newLine();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Edge {
    int from, to; long g;
    public Edge(int f, int to, long g) {
        this.from = f;
        this.to = to;
        this.g = g;
    }
    public String toString(){
        return (this.to + 1)+" "+(this.g);
    }
}

