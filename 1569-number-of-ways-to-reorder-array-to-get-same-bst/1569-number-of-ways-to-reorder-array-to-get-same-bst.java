class Solution {
    static final long MOD = 1000000007;

    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1]
                        + comb[i - 1][j]) % MOD;
            }
        }

        long ans = solve(nums);

        // Remove the original ordering
        return (int)((ans - 1 + MOD) % MOD);
    }

    private long solve(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return 1;
        }

        int root = nums[0];

        int[] leftTemp = new int[n];
        int[] rightTemp = new int[n];

        int leftSize = 0;
        int rightSize = 0;

        // Divide elements into left and right subtree
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                leftTemp[leftSize++] = nums[i];
            } else {
                rightTemp[rightSize++] = nums[i];
            }
        }

        // Create actual arrays
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            left[i] = leftTemp[i];
        }

        for (int i = 0; i < rightSize; i++) {
            right[i] = rightTemp[i];
        }

        // Recursively calculate ways
        long leftWays = solve(left);
        long rightWays = solve(right);

        // Number of ways to interleave left and right
        long interleavings = comb[leftSize + rightSize][leftSize];

        return (((interleavings * leftWays) % MOD)
                * rightWays) % MOD;
    }
}