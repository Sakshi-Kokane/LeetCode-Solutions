class Solution {
    public int missingMultiple(int[] nums, int k) 
    {
        HashSet<Integer> set = new HashSet<>();
        for(int item : nums)
        {
            set.add(item);
        }
        int i=k;
        while(set.contains(i))
        {
                i+=k;
        }
        return i;
    }
}