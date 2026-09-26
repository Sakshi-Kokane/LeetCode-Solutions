class Solution 
{
    public int maxProfit(int[] prices) 
    {
        int n = prices.length;
        int mini=prices[0];
        int profit=0;
        for(int i=1; i<n; i++)
        {
            int cost=prices[i]-mini;
            mini=Integer.min(mini, prices[i]);
            profit=Integer.max(profit, cost);
        }
        return profit;
    }
}