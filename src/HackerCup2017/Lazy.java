package HackerCup2017;

import java.io.*;
import java.util.*;
import java.math.*;

public class Lazy {
    public static void main(String args[]) {
        try {
            InputReader in = new InputReader(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            int tc = in.readInt();
            for (int t = 0; t < tc; t++) {
                int n = in.readInt();
                int w[] = new int[n];
                for (int i = 0; i < n; i++) {
                    w[i] = in.readInt();
                }
                Arrays.sort(w);
                int ans = 0;
                int i = 0;
                int j = n - 1;
                while(true) {
                    int max = w[j];
                    int sum = w[j--];
                    while(sum < 50 && i <= j) {
                        sum = sum + max;
                        i++;
                    }
                    if (sum < 50) {
                        break;
                    }
                    ans++;
                    if (i > j) {
                        break;
                    }
                }
                out.write("Case #"+(t+1)+":");
                out.write(Integer.toString(ans));
                out.newLine();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
