class Solution 
{

public String lexPalindromicPermutation(String s, String target) {
    int n = s.length();
    int[] freq = new int[26];

    for (char ch : s.toCharArray()) {
        freq[ch - 'a']++;
    }

    // Check whether palindrome is possible
    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++) {
        if (freq[i] % 2 == 1) {
            oddCount++;
            middle = (char) ('a' + i);
        }
    }

    if (oddCount != n % 2) {
        return "";
    }

    // Characters available for the left half
    int[] halfFreq = new int[26];

    for (int i = 0; i < 26; i++) {
        halfFreq[i] = freq[i] / 2;
    }

    int halfLength = n / 2;
    String prefix = target.substring(0, halfLength);

    // Find smallest permutation >= prefix
    String left = smallestGreaterOrEqual(halfFreq, prefix);

    if (left == null) {
        return "";
    }

    String palindrome = buildPalindrome(left, middle, n % 2 == 1);

    // If already strictly greater
    if (palindrome.compareTo(target) > 0) {
        return palindrome;
    }

    // Otherwise get the next permutation
    String next = nextPermutation(left);

    if (next == null) {
        return "";
    }

    return buildPalindrome(next, middle, n % 2 == 1);
}

private String smallestGreaterOrEqual(int[] originalFreq, String target) {
    int[] freq = originalFreq.clone();
    StringBuilder result = new StringBuilder();

    int i = 0;

    // Try to match target as much as possible
    while (i < target.length()) {
        int ch = target.charAt(i) - 'a';

        if (freq[ch] > 0) {
            freq[ch]--;
            result.append(target.charAt(i));
            i++;
        } else {
            break;
        }
    }

    // Entire target prefix can be formed
    if (i == target.length()) {
        return result.toString();
    }

    // First, try to increase the current position
    int current = target.charAt(i) - 'a';

    for (int bigger = current + 1; bigger < 26; bigger++) {
        if (freq[bigger] > 0) {
            freq[bigger]--;

            result.append((char) ('a' + bigger));
            appendRemaining(result, freq);

            return result.toString();
        }
    }

    // Otherwise, backtrack and increase a previous position
    for (int j = result.length() - 1; j >= 0; j--) {
        int used = result.charAt(j) - 'a';
        freq[used]++;

        result.deleteCharAt(j);

        for (int bigger = used + 1; bigger < 26; bigger++) {
            if (freq[bigger] > 0) {
                freq[bigger]--;

                StringBuilder answer = new StringBuilder(result);
                answer.append((char) ('a' + bigger));

                appendRemaining(answer, freq);

                return answer.toString();
            }
        }
    }

    return null;
}

private void appendRemaining(StringBuilder result, int[] freq) {
    for (int i = 0; i < 26; i++) {
        while (freq[i] > 0) {
            result.append((char) ('a' + i));
            freq[i]--;
        }
    }
}

private String buildPalindrome(String left, char middle, boolean hasMiddle) {
    StringBuilder answer = new StringBuilder(left);

    if (hasMiddle) {
        answer.append(middle);
    }

    answer.append(new StringBuilder(left).reverse());

    return answer.toString();
}

private String nextPermutation(String s) {
    char[] arr = s.toCharArray();

    int i = arr.length - 2;

    while (i >= 0 && arr[i] >= arr[i + 1]) {
        i--;
    }

    if (i < 0) {
        return null;
    }

    int j = arr.length - 1;

    while (arr[j] <= arr[i]) {
        j--;
    }

    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;

    reverse(arr, i + 1, arr.length - 1);

    return new String(arr);
}

private void reverse(char[] arr, int left, int right) {
    while (left < right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        left++;
        right--;
    }
}
}