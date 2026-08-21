class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Count how many valid amounts are <= x
    long count(long x, int[] coins) {

        int n = coins.length;
        long ans = 0;

        // Try every subset of coins
        for (int mask = 1; mask < (1 << n); mask++) {

            long common = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;
                    common = lcm(common, coins[i]);

                    if (common > x)
                        break;
                }
            }

            if (common > x)
                continue;

            // Inclusion-Exclusion
            if (bits % 2 == 1)
                ans += x / common;
            else
                ans -= x / common;
        }

        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = Long.MAX_VALUE;

        // k * smallest coin is a valid upper bound
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        // Binary Search
        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}