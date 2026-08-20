class Solution {
    public int longestConsecutive(int[] nums) 
    {
        int n = nums.length;
        Arrays.sort(nums);
        int maxCount=0;
        int count=0;
        int last=Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
        {
            if(nums[i]-1==last)
            {
                count++;
                last=nums[i];
            }
            else if(nums[i]!=last)
            {
                count=1;
                last=nums[i];
            }
            maxCount=Math.max(count, maxCount);
        }
        return maxCount;
    }
}