class Solution {
    public boolean isPalindrome(int x) 
    {
        int num = x;
        int reverse=0;
        while(num>0)
        {
            int dig=num%10;
            reverse=reverse*10+dig;
            num=num/10;
        }
        if(x==reverse)
        {
            return true;
        }
        return false;
    }
}