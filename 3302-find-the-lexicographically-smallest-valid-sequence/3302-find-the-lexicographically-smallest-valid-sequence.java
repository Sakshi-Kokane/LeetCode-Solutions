class Solution {

    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // next[i][c] = first index >= i having character c
        int[][] next = new int[n + 1][26];

        Arrays.fill(next[n], n);

        for (int i = n - 1; i >= 0; i--) {

            System.arraycopy(next[i + 1], 0, next[i], 0, 26);

            next[i][word1.charAt(i) - 'a'] = i;
        }

        // prev[i][c] = last index < i having character c
        int[][] prev = new int[n + 1][26];

        Arrays.fill(prev[0], -1);

        for (int i = 0; i < n; i++) {

            System.arraycopy(prev[i], 0, prev[i + 1], 0, 26);

            prev[i + 1][word1.charAt(i) - 'a'] = i;
        }

        /*
         * latest[j][0]:
         * largest possible index for word2[j...]
         * when NO mismatch is allowed.
         *
         * latest[j][1]:
         * largest possible index for word2[j...]
         * when ONE mismatch is allowed.
         *
         * -1 means impossible.
         */
        int[][] latest = new int[m + 1][2];

        latest[m][0] = n;
        latest[m][1] = n;

        for (int j = m - 1; j >= 0; j--) {

            int target = word2.charAt(j) - 'a';

            // -----------------------------------------
            // 1. Exact matching, no mismatch available
            // -----------------------------------------

            int boundaryExact = latest[j + 1][0];

            if (boundaryExact == -1) {

                latest[j][0] = -1;

            } else {

                latest[j][0] =
                        prev[boundaryExact][target];
            }

            // -----------------------------------------
            // 2. One mismatch is available
            // -----------------------------------------

            int boundaryOneMismatch = latest[j + 1][1];

            int exact = -1;

            if (boundaryOneMismatch != -1) {

                exact =
                        prev[boundaryOneMismatch][target];
            }

            // -----------------------------------------
            // 3. Use mismatch at current character
            // -----------------------------------------

            int different = -1;

            if (boundaryExact != -1) {

                for (int c = 0; c < 26; c++) {

                    if (c == target) {
                        continue;
                    }

                    different = Math.max(
                            different,
                            prev[boundaryExact][c]
                    );
                }
            }

            latest[j][1] =
                    Math.max(exact, different);
        }

        // -----------------------------------------
        // Greedily construct answer
        // -----------------------------------------

        int[] answer = new int[m];

        int previous = -1;
        boolean mismatchUsed = false;

        for (int j = 0; j < m; j++) {

            int target = word2.charAt(j) - 'a';

            int start = previous + 1;

            // =========================================
            // Mismatch already used
            // =========================================

            if (mismatchUsed) {

                int candidate =
                        next[start][target];

                /*
                 * Candidate must:
                 * 1. Exist
                 * 2. Leave enough room for exact suffix
                 */
                if (candidate >= n ||
                    latest[j + 1][0] == -1 ||
                    candidate >= latest[j + 1][0]) {

                    return new int[0];
                }

                answer[j] = candidate;
                previous = candidate;
            }

            // =========================================
            // Mismatch is still available
            // =========================================

            else {

                // -------------------------------------
                // Option 1: Exact match
                // -------------------------------------

                int exact =
                        next[start][target];

                if (exact >= n ||
                    latest[j + 1][1] == -1 ||
                    exact >= latest[j + 1][1]) {

                    exact = n;
                }

                // -------------------------------------
                // Option 2: Use mismatch now
                // -------------------------------------

                int mismatch = n;

                for (int c = 0; c < 26; c++) {

                    if (c == target) {
                        continue;
                    }

                    int candidate =
                            next[start][c];

                    if (candidate < n &&
                        latest[j + 1][0] != -1 &&
                        candidate < latest[j + 1][0]) {

                        mismatch =
                                Math.min(
                                        mismatch,
                                        candidate
                                );
                    }
                }

                // -------------------------------------
                // No valid candidate
                // -------------------------------------

                if (exact == n &&
                    mismatch == n) {

                    return new int[0];
                }

                // -------------------------------------
                // Choose smallest index
                // -------------------------------------

                if (mismatch < exact) {

                    answer[j] = mismatch;
                    previous = mismatch;
                    mismatchUsed = true;

                } else {

                    answer[j] = exact;
                    previous = exact;
                }
            }
        }

        return answer;
    }
}