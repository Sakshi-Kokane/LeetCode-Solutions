import java.util.*;

class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of removals
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            }
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, leftRemove, rightRemove, 0, 0, "");

        return new ArrayList<>(result);
    }

    void backtrack(String s, int index,
                   int leftRemove, int rightRemove,
                   int leftCount, int rightCount,
                   String current) {

        // Reached end
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                leftCount == rightCount) {

                result.add(current);
            }

            return;
        }

        char ch = s.charAt(index);

        // If '('
        if (ch == '(') {

            // Option 1: Remove '('
            if (leftRemove > 0) {

                backtrack(
                    s,
                    index + 1,
                    leftRemove - 1,
                    rightRemove,
                    leftCount,
                    rightCount,
                    current
                );
            }

            // Option 2: Keep '('
            backtrack(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                leftCount + 1,
                rightCount,
                current + ch
            );
        }

        // If ')'
        else if (ch == ')') {

            // Option 1: Remove ')'
            if (rightRemove > 0) {

                backtrack(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove - 1,
                    leftCount,
                    rightCount,
                    current
                );
            }

            // Option 2: Keep ')' only if valid
            if (rightCount < leftCount) {

                backtrack(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    leftCount,
                    rightCount + 1,
                    current + ch
                );
            }
        }

        // If it is a letter
        else {

            backtrack(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                leftCount,
                rightCount,
                current + ch
            );
        }
    }
}