class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] mat = new long[size][size];

        // up -> down
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                mat[y][m + x] = 1;
            }
        }

        // down -> up
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) {
                mat[m + y][x] = 1;
            }
        }

        long[] init = new long[size];

        // Length 1:
        // first m states = expecting next move up
        // next m states = expecting next move down
        for (int i = 0; i < m; i++) {
            init[i] = 1;
            init[m + i] = 1;
        }

        long[][] p = power(mat, n - 1);

        long ans = 0;
        for (int i = 0; i < size; i++) {
            long cur = 0;
            for (int j = 0; j < size; j++) {
                cur = (cur + p[i][j] * init[j]) % MOD;
            }
            ans = (ans + cur) % MOD;
        }

        return (int) ans;
    }

    private long[][] power(long[][] base, long exp) {
        int n = base.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long val = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;
                    res[i][j] = (res[i][j] + val * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}