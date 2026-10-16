import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] squareSum;
    long[] lazy;

    void update(int node, int start, int end, int left, int right) {

        if (right < start || end < left) {
            return;
        }

        // Complete overlap
        if (left <= start && end <= right) {

            int len = end - start + 1;

            squareSum[node] =
                (squareSum[node]
                + 2L * sum[node]
                + len) % MOD;

            sum[node] =
                (sum[node] + len) % MOD;

            lazy[node]++;

            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
            (squareSum[node * 2]
            + squareSum[node * 2 + 1]) % MOD;
    }

    void push(int node, int start, int end) {

        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, mid - start + 1, lazy[node]);
        apply(node * 2 + 1, end - mid, lazy[node]);

        lazy[node] = 0;
    }

    void apply(int node, int len, long x) {

        squareSum[node] =
            (squareSum[node]
            + 2L * x % MOD * sum[node] % MOD
            + x * x % MOD * len) % MOD;

        sum[node] =
            (sum[node] + x * len) % MOD;

        lazy[node] += x;
    }

    public int sumCounts(int[] nums) {

        int n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];

        Arrays.fill(last, -1);

        long answer = 0;

        for (int r = 0; r < n; r++) {

            int value = nums[r];

            int prev = last[value];

            /*
             * nums[r] is a new distinct element
             * for all starting positions:
             *
             * prev + 1 ... r
             */
            update(1, 0, n - 1, prev + 1, r);

            /*
             * squareSum[1] =
             * sum of distinct_count^2
             * for all subarrays ending at r
             */
            answer =
                (answer + squareSum[1]) % MOD;

            last[value] = r;
        }

        return (int) answer;
    }
}