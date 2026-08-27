class Solution {
    public String lexGreaterPermutation(String s, String target) 
    {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) 
        {
            freq[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();
        int i = 0;

        // Match target as much as possible
        while (i < s.length() && freq[target.charAt(i) - 'a'] > 0) {
            char ch = target.charAt(i);
            ans.append(ch);
            freq[ch - 'a']--;
            i++;
        }

        // Try from current position, then backtrack
        while (i >= 0) {

            // Find smallest character greater than target[i]
            if (i < s.length()) {
                int targetChar = target.charAt(i) - 'a';

                for (int ch = targetChar + 1; ch < 26; ch++) {
                    if (freq[ch] > 0) {
                        StringBuilder result = new StringBuilder(
                            ans.substring(0, i)
                        );

                        result.append((char) ('a' + ch));
                        freq[ch]--;

                        // Add remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {
                            while (freq[j]-- > 0) {
                                result.append((char) ('a' + j));
                            }
                        }

                        return result.toString();
                    }
                }
            }

            // Backtrack: restore previous character
            i--;

            if (i >= 0) {
                char ch = ans.charAt(i);
                freq[ch - 'a']++;
            }
        }

        return "";
    }
}