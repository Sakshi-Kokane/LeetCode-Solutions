class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M] = maximum stones current player can get
        // starting from i with M
        int[][] dp = new int[n][n + 1];

        for (int i = n - 1; i >= 0; i--) {

            for (int M = 1; M <= n; M++) {

                // If current player can take all remaining piles
                if (i + 2 * M >= n) {
                    dp[i][M] = suffix[i];
                    continue;
                }

                int best = 0;

                // Try taking X piles, where 1 <= X <= 2M
                for (int X = 1; X <= 2 * M && i + X <= n; X++) {

                    // Current player gets suffix[i] - stones opponent can get
                    int opponent = dp[i + X][Math.max(M, X)];

                    int current = suffix[i] - opponent;

                    best = Math.max(best, current);
                }

                dp[i][M] = best;
            }
        }

        return dp[0][1];
    }
}