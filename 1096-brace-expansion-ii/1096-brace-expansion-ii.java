class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    // Parse one complete expression
    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();

        // Current concatenation result
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            // Comma means UNION
            if (ch == ',') {

                result.addAll(current);

                current.clear();
                current.add("");

                index++;
            }

            // Opening brace -> recursively parse inside
            else if (ch == '{') {

                index++; // skip '{'

                Set<String> inside = parse(s);

                index++; // skip '}'

                current = concatenate(current, inside);
            }

            // Lowercase letter
            else {

                String letter = String.valueOf(ch);

                Set<String> letterSet = new HashSet<>();
                letterSet.add(letter);

                current = concatenate(current, letterSet);

                index++;
            }
        }

        // Add last concatenation group
        result.addAll(current);

        return result;
    }

    // Cartesian-product concatenation
    private Set<String> concatenate(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {

            for (String y : b) {

                result.add(x + y);
            }
        }

        return result;
    }
}