class Solution {
    public int mostWordsFound(String[] sentences) 
    {
        int maxCount=0;
        for(String str : sentences)
        {
            maxCount=Math.max(maxCount, str.split(" ").length);
        } 
        return maxCount;   
    }
}