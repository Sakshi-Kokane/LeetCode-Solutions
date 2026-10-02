class Solution 
{
    public List<String> generateParenthesis(int n) 
    {
        List<String> result = new ArrayList<>();

        generate(0, 0, n, "", result);
        return result;
    }
    public void generate(int open, int close, int n, String current, List<String> result)
    {
        if(open == n && close == n)
        {
            result.add(current);
            return;
        }
        if(open < n)
        {
            generate(open+1, close, n, current+"(", result);
        }
        if(close < open)
        {
            generate(open, close+1, n, current+')', result);
        }
    }
}