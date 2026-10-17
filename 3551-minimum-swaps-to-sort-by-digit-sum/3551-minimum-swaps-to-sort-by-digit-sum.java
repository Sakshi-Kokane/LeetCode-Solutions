class Solution 
{
    public int minSwaps(int[] nums) 
    {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++)
        {
             arr[i][0] = nums[i];
             arr[i][1] = digitSum(nums[i]);
        }
        Arrays.sort(arr, (a,b)->{
            if (a[1] != b[1])
            {
                 return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
           
        });
        HashMap<Integer, Integer> indexMap = new HashMap<>();

        for(int i=0; i<n; i++)
        {
            indexMap.put(nums[i], i);
        }
        boolean[] visited = new boolean[n];
        int  swaps = 0;
        for(int i=0; i<n; i++)
        {
            if(visited[i])
            {
                continue;
            }
            int currentIndex = indexMap.get(arr[i][0]);
            if(currentIndex == i)
            {
                visited[i] = true;
                continue;
            }
            int cycleSize = 0;
            while(!visited[currentIndex])
            {
                visited[currentIndex] = true;
                cycleSize++;
                currentIndex = indexMap.get(arr[currentIndex][0]);
            } 
            swaps += cycleSize-1;
        }
        return swaps;
        
    }
    int digitSum(int num)
    {
        int sum = 0;
        while(num > 0)
        {
            sum += num%10;
            num/=10;
        }
        return sum;
    }
}