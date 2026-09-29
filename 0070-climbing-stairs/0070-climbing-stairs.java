class Solution 
{
    int dp[];
    int f(int idx)
    {
        if(idx==0 || idx==1)
        {
            return 1;
        }
        if(dp[idx]!=-1)
        {
            return dp[idx];
        }
        int left = f(idx-1);
        int right = f(idx-2);
        dp[idx]=left+right;
        return dp[idx];
        
    }
    public int climbStairs(int n) 
    {
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        return f(n);
    }
}