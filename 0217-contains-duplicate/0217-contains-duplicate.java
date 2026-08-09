class Solution {
    public boolean containsDuplicate(int[] nums) 
    {
        //Optimal
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums)
        {
            if(!set.add(num))
            {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}