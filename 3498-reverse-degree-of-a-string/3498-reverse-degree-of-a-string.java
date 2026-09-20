class Solution 
{
    public int reverseDegree(String s) 
    {
        int product=0;

        for(int i=0; i<s.length(); i++)
        {
            int digit=(26-(s.charAt(i)-'a'))*(i+1);
            product+=digit;
        }
        return product;
    }
}