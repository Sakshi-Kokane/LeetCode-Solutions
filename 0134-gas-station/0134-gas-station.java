class Solution 
{
    public int canCompleteCircuit(int[] gas, int[] cost) 
    {
        int total = 0;
        int start = 0;
        int tank = 0;
        for(int i=0; i<gas.length; i++)
        {
            int difference = gas[i] - cost[i];
            total+=difference;
            tank += difference;
            if(tank<0)
            {
                start=i+1;
                tank=0;
            }
        }
        if(total<0)
        {
            return -1;
        }
        return start;
    }
}