class Solution {
    public int majorityElement(int[] nums) 
    {
        int count=0;
        int element=-1;
        for(int num : nums)
        {
            if(count==0)
            {
                element=num;
            }
            if(num==element)
            {
                count++;
            }
            else if(num!=element)
            {
                count--;
            }

        }
        
        return element;
    }
}