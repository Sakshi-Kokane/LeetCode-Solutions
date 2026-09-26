class Solution 
{
    public int singleNumber(int[] nums) 
    {
        int element=0;
        for(int num : nums)
        {
            element^=num;
        }
        return element;
    }
}