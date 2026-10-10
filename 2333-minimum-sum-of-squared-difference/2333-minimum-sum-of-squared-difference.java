class Solution {
public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
int[] freq = new int[100001];
long k = (long) k1 + k2;
long total = 0;

    for (int i = 0; i < nums1.length; i++) {
        int d = Math.abs(nums1[i] - nums2[i]);
        freq[d]++;
        total += d;
    }

    if (k >= total) {
        return 0;
    }

    for (int d = 100000; d > 0 && k > 0; d--) {
        if (freq[d] == 0) {
            continue;
        }

        int next = d - 1;
        long operations = Math.min(k, (long) freq[d]);

        freq[d] -= (int) operations;
        freq[next] += (int) operations;
        k -= operations;
    }

    long answer = 0;

    for (int d = 1; d <= 100000; d++) {
        answer += (long) d * d * freq[d];
    }

    return answer;
}

}