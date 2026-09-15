class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {

            // We can always skip this character
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // Odd length palindrome
            int left = i;
            int right = i;

            while (left >= 0 && right < n &&
                   s.charAt(left) == s.charAt(right)) {

                if (right - left + 1 >= k) {
                    dp[right + 1] =
                        Math.max(dp[right + 1], dp[left] + 1);
                }

                left--;
                right++;
            }

            // Even length palindrome
            left = i;
            right = i + 1;

            while (left >= 0 && right < n &&
                   s.charAt(left) == s.charAt(right)) {

                if (right - left + 1 >= k) {
                    dp[right + 1] =
                        Math.max(dp[right + 1], dp[left] + 1);
                }

                left--;
                right++;
            }
        }

        return dp[n];
    }
}