class Solution {
    public int[] resultArray(int[] nums) 
    {
        int n=nums.length;
        int arr1[]=new int[n];
        int arr2[]= new int[n];
        arr1[0]=nums[0];
        arr2[0]=nums[1];
        int i=0;
        int j=0;
        for(int k=2; k<n; k++)
        {
            if(arr1[i]>arr2[j])
            {
                arr1[++i]=nums[k];
            }
            else
            {
                arr2[++j]=nums[k];
            }
        }
        int result[] = new int[n];
        int k=0;
        for(k=0; k<=i; k++)
        {
            result[k]=arr1[k];
        }
        int p=0;
        for(int s=k; s<=k+j; s++)
        {
            result[s]=arr2[p++];
        }
        return result;
    }
}