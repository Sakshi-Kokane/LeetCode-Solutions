class Solution 
{
    public int minInsertions(String s) 
    {
        int open = 0;
        int insertions = 0;

        int i=0;
        while(i < s.length())
        {
            char ch = s.charAt(i);

            if(ch == '(')
            {
                open++;
                i++;
            }
            else
            {
                if(i+1 < s.length() && s.charAt(i+1) == ')')
                {
                    if(open > 0)
                    {
                        open--;
                    }
                    else
                    {
                        insertions++;
                    }
                    i+=2;
                }
                else
                {
                    if(open > 0)
                    {
                        open--;
                        insertions++;
                    }
                    else
                    {
                        insertions +=2;
                    }
                    i++;
                }
            }
        }
        insertions += open*2;
        return insertions;
    }
}