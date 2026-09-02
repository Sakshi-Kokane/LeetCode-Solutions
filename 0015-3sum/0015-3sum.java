class Solution {
    public List<List<Integer>> threeSum(int[] nums) 
    {
        //Better approach
        Set<List<Integer>> ans = new HashSet<>();
        int n=nums.length;
        for(int i=0; i<n; i++)
        {
            HashSet<Integer> set = new HashSet<>();
            for(int j=i+1; j<n; j++)
            {
                int third=-(nums[i]+nums[j]);
                if(set.contains(third))
                {
                    List<Integer> result = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(result);
                    ans.add(result);
                }
                set.add(nums[j]);
            }
        } 
        return new ArrayList<>(ans);   
    }
}