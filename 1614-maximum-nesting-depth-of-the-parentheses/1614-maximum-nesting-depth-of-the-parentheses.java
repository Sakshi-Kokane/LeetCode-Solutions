class Solution 
{
    public int maxDepth(String s) 
    {
        int max=0;
        int currmax=0;
        for(int i=0;  i<s.length(); i++)
        {
            if(s.charAt(i)=='(')
            {
                currmax++;
            }
            else if(s.charAt(i)==')')
            {
                currmax--;
            }
            max=Integer.max(max, currmax);
        }
        return max;
    }
}