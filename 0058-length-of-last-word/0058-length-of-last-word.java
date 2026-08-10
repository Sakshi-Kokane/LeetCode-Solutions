class Solution {
    public int lengthOfLastWord(String s) 
    {
        s=s.trim();
        String[] words = s.split(" ");
        int index = words.length-1;
        return words[index].length(); 
    }
}