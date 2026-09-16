class Solution {
    static final long MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        // factorial
        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        // inverse factorial
        invFact[N] = power(fact[N], MOD - 2);

        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        return (int)(
            fact[N] *
            invFact[2 * k] % MOD *
            invFact[N - 2 * k] % MOD
        );
    }

    private long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}