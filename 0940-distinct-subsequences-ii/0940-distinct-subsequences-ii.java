class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        // dp = number of distinct subsequences including empty subsequence
        long dp = 1;

        // last[c] = number of subsequences before the previous occurrence of c
        long[] last = new long[26];

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';

            long newDp = (2 * dp - last[idx] + MOD) % MOD;

            // For the next occurrence of this character
            last[idx] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}