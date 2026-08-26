class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        int left = 0;
        int ones = 0;

        int bestStart = -1;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We have exactly k ones
            if (ones == k) {

                // Remove unnecessary leading zeros
                while (left <= right && s.charAt(left) == '0') {
                    left++;
                }

                int currentLength = right - left + 1;

                // Better length
                if (currentLength < bestLength) {
                    bestLength = currentLength;
                    bestStart = left;
                }

                // Same length -> lexicographically smaller
                else if (currentLength == bestLength) {
                    if (s.substring(left, right + 1)
                         .compareTo(s.substring(bestStart, bestStart + bestLength)) < 0) {
                        bestStart = left;
                    }
                }

                // Move left past the first 1
                left++;
                ones--;
            }
        }

        if (bestStart == -1) {
            return "";
        }

        return s.substring(bestStart, bestStart + bestLength);
    }
}