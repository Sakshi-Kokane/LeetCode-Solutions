class Solution {
    public int mostWordsFound(String[] sentences) 
    {
        int maxCount=0;
        for(String str : sentences)
        {
            String word[] = str.split(" ");
            maxCount=Math.max(maxCount, word.length);
        } 
        return maxCount;   
    }
}