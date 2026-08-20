class Solution {
    public int longestConsecutive(int[] nums) 
    {
        int n = nums.length;
        int maxLength=0;
        if(n==0)
        {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++)
        {
            set.add(nums[i]);
        }
        for(int x: set)
        {
            if(!set.contains(x-1))
            {
                int cnt=1;
                //int item=x;
                while(set.contains(x+1))
                {
                    x=x+1;
                    cnt++;
                }
                maxLength=Math.max(cnt, maxLength);
            }
        }
        return  maxLength;
    }
}