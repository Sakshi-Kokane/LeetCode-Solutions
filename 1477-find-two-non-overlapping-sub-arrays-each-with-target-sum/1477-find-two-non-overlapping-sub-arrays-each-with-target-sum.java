class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        int[] best = new int[n];

        // Initially, no valid subarray
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;

        int answer = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if (sum == target) {

                int currentLength = right - left + 1;

                // Combine with best non-overlapping subarray
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]
                    );
                }

                // Update shortest subarray seen so far
                minLength = Math.min(minLength, currentLength);
            }

            // Store best answer up to 'right'
            best[right] = minLength;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}