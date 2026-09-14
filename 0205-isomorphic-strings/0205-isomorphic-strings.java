class Solution 
{
    public boolean isIsomorphic(String s, String t) 
    {
        if(s.length() != t.length())
        {
            return false;
        }
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();
        for(int i=0; i<s.length(); i++)
        {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if(mapS.containsKey(a))
            {
                if(mapS.get(a)!=b)
                {
                    return false;
                }
            }
            else
            {
                mapS.put(a,b);
            }

            if(mapT.containsKey(b))
            {
                if(mapT.get(b)!=a)
                {
                    return false;
                }
            }
            else
            {
                mapT.put(b,a);
            }
        }
        return true;
    }
}