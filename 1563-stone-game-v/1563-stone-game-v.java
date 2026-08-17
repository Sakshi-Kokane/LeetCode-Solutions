class Solution 
{
    int[][] dp;
    int prefix[];
    public int stoneGameV(int[] stoneValue) 
    {
        int n = stoneValue.length;
        dp = new int[n][n];

        for(int i=0; i<n; i++)
        {
            Arrays.fill(dp[i], -1);
        }
        prefix = new int[n+1];
        for(int i=0; i<n; i++)
        {
            prefix[i+1]=prefix[i]+stoneValue[i];
        }
        return solve(0, n-1);
    }
    private int solve(int i, int j)
    {
        if(i==j)
        {
            return 0;
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }
        int ans = 0;
        for(int k=i; k<j; k++)
        {
            int leftSum = prefix[k+1]-prefix[i];
            int rightSum = prefix[j+1]-prefix[k+1];
            if(leftSum<rightSum)
            {
                ans = Math.max(ans, leftSum+solve(i, k));
            }
            else if(rightSum < leftSum)
            {
                ans = Math.max(ans, rightSum+solve(k+1, j));
            }
            else
            {
                ans = Math.max(ans, leftSum+solve(i, k));
                ans = Math.max(ans, rightSum+solve(k+1, j));
            }
        }
        return dp[i][j]=ans;
    }
}