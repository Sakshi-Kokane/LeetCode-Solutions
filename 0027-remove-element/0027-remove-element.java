class Solution 
{
    public int removeElement(int[] nums, int val) 
    {
        int n = nums.length;
        int i=0;
        int j=n-1;
        while(i<=j)
        {
            while(j>=i && nums[j]==val)
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