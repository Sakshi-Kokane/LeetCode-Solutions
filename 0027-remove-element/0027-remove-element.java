class Solution {
    public int removeElement(int[] nums, int val) 
    {
        int i=0;
        int j=nums.length-1;
        while(i<=j)
        {
            while(j>=0 && nums[j]==val)
            {
                 j--;
            }
            if(i>j)
            {
                break;
            }
            if(nums[i]==val)
            {
                nums[i]=nums[j];
                j--;
            }
            i++;
        }
        return i;
    }
}