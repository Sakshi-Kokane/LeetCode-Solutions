class Solution 
{
    public int reverse(int x) 
    {
        int reverse=0;
        int n=x;
        while(x!=0)
        {
            int r=x%10;
            x=x/10;
            if(reverse>Integer.MAX_VALUE/10 ||
            reverse == Integer.MAX_VALUE/10 && r>7)
            {
                return 0;
            }
            if(reverse<Integer.MIN_VALUE/10 ||
            reverse == Integer.MIN_VALUE/10 && r<-8)
            {
                return 0;
            }
            reverse=reverse*10+r; 
        }  
        return reverse;  
    }
}