class Solution {
    public int[] searchRange(int[] nums, int target)
    {
        int first,last;
        first=findFirst(nums, target);
        last=findLast(nums, target);  
        return new int[]{first, last}; 
    }
    public int findFirst(int nums[], int target)
    {
        int f=-1;
        int low=0;
        int high=nums.length-1;
        int mid;
        while(low<=high)
        {
            mid=(low+high)/2;
            if(target==nums[mid])
            {
                f=mid;
                high=mid-1;
            }
            else if(target>nums[mid])
            {
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        return f;
    }
    public int findLast(int nums[], int target)
    {
        int l=-1;
        int low=0;
        int high=nums.length-1;
        int mid;
        while(low<=high)
        {
            mid=(low+high)/2;
            if(target==nums[mid])
            {
                l=mid;
                low=mid+1;
            }
            else if(target>nums[mid])
            {
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        return l;
    }
}