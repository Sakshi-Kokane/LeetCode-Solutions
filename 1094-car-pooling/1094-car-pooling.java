class Solution 
{
    public boolean carPooling(int[][] trips, int capacity) 
    {
        int[] passengers = new int[1001];
        for(int[] trip : trips)
        {
            int numPassangers = trip[0];
            int from = trip[1];
            int to = trip[2];
            passengers[from]+=numPassangers;
            passengers[to]-=numPassangers;
        }
        int currentPassengers=0;
        for(int i=0; i<=1000; i++)
        {
            currentPassengers+=passengers[i];
            if(currentPassengers>capacity)
            {
                return false;
            }
        }
        return true;
    }
}