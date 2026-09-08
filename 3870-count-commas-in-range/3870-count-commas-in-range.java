class Solution {
    public int countCommas(int n) 
    {
        int ans = 0;
        int power = 1000;
        while(power<=n)
        {
            ans += n-power+1;
            power *= 1000;
        }
        return ans;
    }
}