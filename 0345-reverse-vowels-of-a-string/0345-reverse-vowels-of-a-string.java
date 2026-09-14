class Solution 
{
    public String reverseVowels(String s) 
    {
        StringBuffer sb = new StringBuffer(s);
        int i=0;
        int j=s.length()-1;
        while(i<j)
        {
            while(i<j && !isVowel(sb.charAt(i)))
            {
                i++;
            }
            while(i<j && !isVowel(sb.charAt(j)))
            {
                j--;
            }
            char temp=sb.charAt(i);
            sb.setCharAt(i, s.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
        return sb.toString();
    }
    boolean isVowel(char ch)
    {
        return ch == 'A' || ch=='E' || ch=='I'|| ch=='O' || ch=='U' ||
        ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
    }
}