class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) 
    {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for(int i=0; i<heights.length-1; i++)
        {
            int climb = heights[i+1]-heights[i];
            if(climb<=0)
            {
                continue;
            }
            minheap.offer(climb);
            if(minheap.size()>ladders)
            {
                bricks-=minheap.poll();
            }
            if(bricks<0)
            {
                return i;
            }
        }
        return heights.length-1;
    }
}