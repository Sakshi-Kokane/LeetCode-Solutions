class Solution 
{
    public int largestInteger(int[] nums, int k) 
    {
        int[] count = new int[51];

        // Generate every subarray of size k
        for(int i = 0; i <= nums.length - k; i++)
        {
            boolean[] seen = new boolean[51];

            for(int j = i; j < i + k; j++)
            {
                if(!seen[nums[j]])
                {
                    count[nums[j]]++;
                    seen[nums[j]] = true;
                }
            }
        }

        int ans = -1;

        // Find the largest number appearing in exactly one subarray
        for(int i = 0; i <= 50; i++)
        {
            if(count[i] == 1)
            {
                ans = i;
            }
        }

        return ans;
    }
}