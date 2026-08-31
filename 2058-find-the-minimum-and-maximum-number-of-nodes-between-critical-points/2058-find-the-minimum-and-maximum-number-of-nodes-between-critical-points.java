/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution 
{
    public int[] nodesBetweenCriticalPoints(ListNode head) 
    {
        if(head == null || head.next==null || head.next.next==null)
        {
            return new int[]{-1, -1};
        }
        int minDistance = Integer.MAX_VALUE;

        ListNode previous = head;
        ListNode current = head.next;
        int position = 2;
        
        int firstCritical = -1;
        int previousCritical = -1;
        int lastCritical = -1;
        while(current.next != null)
        {
            ListNode next = current.next;
            boolean isLocalMax = current.val>previous.val && current.val>next.val;
            boolean isLocalMin = current.val<previous.val && current.val<next.val;
            if(isLocalMin || isLocalMax)
            {
                if(firstCritical == -1)
                {
                    firstCritical = position;
                }
                else
                {
                    minDistance=Math.min(minDistance, position-previousCritical);
                }
                previousCritical = position;
                lastCritical = position;
            }
            previous = current;
            current = current.next;
            position++;
        }
        if(firstCritical == lastCritical)
        {
            return new int[]{-1, -1};
        }
        int maxDistance = lastCritical - firstCritical;
        return new int[]{minDistance, maxDistance};
    }
}