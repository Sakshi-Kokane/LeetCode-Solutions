class Solution {
    public int missingNumber(int[] nums) 
    {
        int act_sum=0;
        for(int num : nums)
        {
            act_sum+=num;
        }
        int exp_sum=0;
        for(int i=0; i<=nums.length; i++)
        {
            exp_sum+=i;
        }
        return exp_sum-act_sum;
    }
}